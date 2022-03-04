package com.junemon.compose_stable.feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.presentation.model.UiState
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.screen.DetailPokemonStatState
import com.junemon.compose_stable.screen.DetailScreenAreaState
import com.junemon.compose_stable.screen.DetailScreenCharacteristicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailMviViewModel @Inject constructor(
    private val dataUseCase: PokemonUseCase,
    private val screenUseCase: ScreensUseCase
) : ViewModel() {


    private val _selectedPokemonId = MutableStateFlow(0)
    val selectedPokemonId = _selectedPokemonId.asStateFlow()


    private val _areaState: MutableStateFlow<DetailScreenAreaState> = MutableStateFlow(
        DetailScreenAreaState.initial()
    )
    val areaState = _areaState.asStateFlow()

    private val _characteristicState: MutableStateFlow<DetailScreenCharacteristicState> =
        MutableStateFlow(
            DetailScreenCharacteristicState.initial()
        )
    val characteristicState = _characteristicState.asStateFlow()

    private val _statState: MutableStateFlow<DetailPokemonStatState> =
        MutableStateFlow(
            DetailPokemonStatState.initial()
        )
    val statState = _statState.asStateFlow()

    fun setSelectedPokemonId(id: Int) {
        _selectedPokemonId.value = id
    }

    init {
        viewModelScope.launch {
            selectedPokemonId.flatMapLatest {
                combine(
                    dataUseCase.getPokemonLocationAreas(it),
                    dataUseCase.getDetailPokemonCharacteristic(it),
                    dataUseCase.getPokemonById(it)
                ) { a, b, c ->
                    Triple(a, b, c)
                }
            }.collect {
                consumePokemonAreaById(it.first)
                consumeDetailPokemonCharacteristicById(it.second)
                consumePokemonDetailById(it.third)
            }
        }
    }


    private fun consumePokemonAreaById(data: UiState<List<String>>) {
        when (data) {
            is UiState.Content ->
                _areaState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, data = data.data)
                }
            is UiState.Error ->
                _areaState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, failedMessage = data.message)
                }
        }
    }

    private fun consumeDetailPokemonCharacteristicById(data: UiState<String>) {
        when (data) {
            is UiState.Content ->
                _characteristicState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, data = data.data)
                }
            is UiState.Error ->
                _characteristicState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, failedMessage = data.message)
                }
        }
    }

    private fun consumePokemonDetailById(data: UiState<PokemonDetail>) {
        when (data) {
            is UiState.Content ->
                _statState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, data = data.data)
                }
            is UiState.Error ->
                _statState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, failedMessage = data.message)
                }
        }
    }

    @Composable
    fun provideCoilImageLoader(): ImageLoader = screenUseCase.provideCoilImageLoader()

    @Composable
    fun provideCoilImageRequest(imageUrl: String): ImageRequest =
        screenUseCase.provideCoilImageRequest(
            imageUrl = imageUrl
        )


    @Composable
    fun ListOfPokemonSprite(pokemonItem: PokemonDetail, modifier: Modifier = Modifier) =
        screenUseCase.ListOfPokemonSprite(
            pokemonItem = pokemonItem,
            modifier = modifier
        )

    @Composable
    fun DetailStatRow(firstText: String, secondText: String, modifier: Modifier = Modifier) =
        screenUseCase.DetailStatRow(
            firstText = firstText,
            secondText = secondText,
            modifier = modifier
        )

    @Composable
    fun PokemonDetailStat(pokemonItem: PokemonDetail, modifier: Modifier = Modifier) =
        screenUseCase.PokemonDetailStat(
            pokemonItem = pokemonItem,
            modifier = modifier
        )

    @Composable
    fun DetailTypeRow(
        firstText: String,
        secondText: String,
        thirdText: String, modifier: Modifier = Modifier
    ) = screenUseCase.DetailTypeRow(
        firstText = firstText,
        secondText = secondText,
        thirdText = thirdText,
        modifier = modifier
    )

    @Composable
    fun PokemonDetailType(pokemonItem: PokemonDetail, modifier: Modifier = Modifier) =
        screenUseCase.PokemonDetailType(
            pokemonItem = pokemonItem,
            modifier = modifier
        )

    @Composable
    fun DetailCharacteristicRow(characteristic: String, modifier: Modifier = Modifier) = screenUseCase.DetailCharacteristicRow(
        characteristic = characteristic,
        modifier = modifier
    )

    @Composable
    fun DetailAreaRow(modifier: Modifier = Modifier, areas: List<String>) = screenUseCase.DetailAreaRow(
        areas = areas,
        modifier = modifier
    )


    @Composable
    fun LottieLoading(loadingSize: Dp) = screenUseCase.LottieLoading(loadingSize)
}
package com.junemon.compose_stable.feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.base.Reducer
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.presentation.model.UiState
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.screen.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ian Damping on 21,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class PokemonMviViewModel @Inject constructor(
    private val dataUseCase: PokemonUseCase,
    private val screenUseCase: ScreensUseCase
) :
    ViewModel() {

    private val _state: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.initial())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataUseCase.getPokemon().collect {
                when (val data = it) {
                    is UiState.Content -> _state.update { currentUiState ->
                        currentUiState.copy(isLoading = false, data = data.data)
                    }

                    is UiState.Error -> _state.update { currentUiState ->
                        currentUiState.copy(isLoading = false, failedMessage = data.message)
                    }
                }

            }
        }
    }

    @Composable
    fun ListPokemon(
        listOfPokemon: List<PokemonDetail>,
        selectPokemon: (PokemonDetail) -> Unit,
        modifier: Modifier
    ) = screenUseCase.ListPokemon(listOfPokemon, selectPokemon, modifier)

    @Composable
    fun LottieLoading(loadingSize: Dp) = screenUseCase.LottieLoading(loadingSize)

}
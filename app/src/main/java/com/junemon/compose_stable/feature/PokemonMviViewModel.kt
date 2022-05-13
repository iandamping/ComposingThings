package com.junemon.compose_stable.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.data.datasource.cache.PokemonEntity
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.presentation.model.UiState
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.screen.HomeScreenCachedState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    var uiStateCached by mutableStateOf(HomeScreenCachedState.initial())
        private set

    init {
        viewModelScope.launch {
            dataUseCase.getCachedPokemon.collect {
                uiStateCached = when (it) {
                    is UiState.Content ->
                        uiStateCached.copy(isLoading = false, data = it.data)

                    is UiState.Error ->
                        uiStateCached.copy(isLoading = false, failedMessage = it.message)
                }
            }
        }
    }

    @Composable
    fun ListPokemon(
        listOfPokemon: List<PokemonEntity>,
        selectPokemon: (PokemonEntity) -> Unit,
        modifier: Modifier
    ) = screenUseCase.ListPokemon(listOfPokemon, selectPokemon, modifier)

    @Composable
    fun LottieLoading(loadingSize: Dp) = screenUseCase.LottieLoading(loadingSize)

}
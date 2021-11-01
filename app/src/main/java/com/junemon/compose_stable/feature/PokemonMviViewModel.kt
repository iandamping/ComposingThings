package com.junemon.compose_stable.feature

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.base.BaseViewModel
import com.junemon.compose_stable.base.Reducer
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.presentation.model.UiState
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.screen.HomeScreenState
import com.junemon.compose_stable.screen.HomeScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    BaseViewModel<HomeScreenState>() {

    private val reducer = MainReducer(HomeScreenState.initial())

    override val state: StateFlow<HomeScreenState>
        get() = reducer.state

    fun loadData() {
        consumeSuspend {
            dataUseCase.getPokemon().collect {
                when (val data = it) {
                    is UiState.Content -> sendEvent(HomeScreenUiEvent.ShowData(data.data))
                    is UiState.Error -> sendEvent(HomeScreenUiEvent.FailedMessage(data.message))
                }

            }
        }
    }

    private fun sendEvent(event: HomeScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private class MainReducer(initial: HomeScreenState) :
        Reducer<HomeScreenState, HomeScreenUiEvent>(initial) {
        override fun reduce(oldState: HomeScreenState, event: HomeScreenUiEvent) {
            when (event) {
                is HomeScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is HomeScreenUiEvent.FailedMessage ->
                    setState(oldState.copy(isLoading = false, failedMessage = event.message))
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

    @Composable
    fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean = true,
        onBack: () -> Unit
    ) = screenUseCase.BackHandler(backDispatcher = backDispatcher, enabled = true) {
        onBack.invoke()
    }
}
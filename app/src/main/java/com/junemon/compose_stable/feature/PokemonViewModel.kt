package com.junemon.compose_stable.feature

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import com.junemon.compose_stable.core.domain.model.UiState
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Ian Damping on 24,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val dataUseCase: PokemonUseCase,
    private val screenUseCase: ScreensUseCase
) : ViewModel() {


    fun getPokemon(): Flow<UiState<List<PokemonDetail>>> = dataUseCase.getPokemon()

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
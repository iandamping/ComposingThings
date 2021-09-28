package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import com.junemon.compose_stable.core.domain.response.PokemonDetail

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ScreensUseCase {

    @Composable
    fun ListPokemon(listOfPokemon: List<PokemonDetail>, selectPokemon: (PokemonDetail) -> Unit, modifier: Modifier)

    @Composable
    fun LottieLoading(loadingSize:Dp)

    @Composable
    fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean,
        onBack: () -> Unit
    )
}
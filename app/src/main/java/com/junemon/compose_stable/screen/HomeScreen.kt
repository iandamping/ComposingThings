package com.junemon.compose_stable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.feature.PokemonMviViewModel
import timber.log.Timber


/**
 * Created by Ian Damping on 28,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Composable
fun HomeScreen(
    pokemonMviVm: PokemonMviViewModel,
    argument: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val cachedState = pokemonMviVm.uiStateCached

    when{
        cachedState.isLoading -> pokemonMviVm.LottieLoading(loadingSize = 200.dp)
        cachedState.data.isNotEmpty() -> pokemonMviVm.ListPokemon(
            listOfPokemon = cachedState.data,
            modifier = modifier,
            selectPokemon = { selectedPokemon ->
                argument.invoke(
                    selectedPokemon.pokemonId
                )
            })
        cachedState.failedMessage.isNotEmpty() -> Timber.e("error : ${cachedState.failedMessage}")

    }
}
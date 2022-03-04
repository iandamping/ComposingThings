package com.junemon.compose_stable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.junemon.compose_stable.feature.PokemonMviViewModel
import com.junemon.compose_stable.navigation.ConstantNavigation
import com.junemon.compose_stable.navigation.NavigationArgs
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

    val lifecycleOwner = LocalLifecycleOwner.current

    val mviFlowLifecycle = remember(pokemonMviVm.state, lifecycleOwner) {
        pokemonMviVm.state.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by mviFlowLifecycle.collectAsState(HomeScreenState.initial())


    when {
        state.isLoading -> pokemonMviVm.LottieLoading(loadingSize = 200.dp)
        state.data.isNotEmpty() -> pokemonMviVm.ListPokemon(
            listOfPokemon = state.data,
            modifier = modifier,
            selectPokemon = { selectedPokemon ->
                argument.invoke(
                    selectedPokemon.pokemonId
                )
            })
        state.failedMessage.isNotEmpty() -> {
            Timber.e("errorn : ${state.failedMessage}")
        }
    }
}
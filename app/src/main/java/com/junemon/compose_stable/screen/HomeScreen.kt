package com.junemon.compose_stable.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.junemon.compose_stable.core.domain.model.UiState
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.feature.PokemonViewModel
import timber.log.Timber


/**
 * Created by Ian Damping on 28,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Composable
fun HomeScreen(
    pokemonVm: PokemonViewModel,
//    navControler: NavHostController,
    modifier: Modifier = Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val pokemonFlowLifecycleAware = remember(pokemonVm.getPokemon(), lifecycleOwner) {
        pokemonVm.getPokemon().flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val pokemonResult by pokemonFlowLifecycleAware.collectAsState(initial = UiState.Loading)

    when (val pokemonData: UiState<List<PokemonDetail>> = pokemonResult) {
        is UiState.Content -> pokemonVm.ListPokemon(
            listOfPokemon = pokemonData.data,
            modifier = modifier,
            selectPokemon = { selectedPokemon ->
                Timber.e("select pokemon : ${selectedPokemon.pokemonImage}")
//                viewModel.setNewsDetail(Gson().toJson(it))
//                navController.navigate(ScreensNavigation.LoadDetail().name)
                // navigateToDetailNews(navController = navController, newsDetail = it.sourceName)
            })

        is UiState.Error -> Toast.makeText(
            LocalContext.current,
            pokemonData.message,
            Toast.LENGTH_SHORT
        ).show()

        UiState.Loading -> pokemonVm.LottieLoading(loadingSize = 200.dp)
    }
}
package com.junemon.compose_stable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.junemon.compose_stable.feature.PokemonDetailMviViewModel
import com.junemon.compose_stable.feature.PokemonMviViewModel
import com.junemon.compose_stable.screen.DetailScreen
import com.junemon.compose_stable.screen.HomeScreen

@Composable
fun ScreenNavigation(
    pokemonMviVm: PokemonMviViewModel,
    detailPokemonVm: PokemonDetailMviViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ConstantNavigation.LoadHome().name,
        modifier = modifier
    ) {
        composable(ConstantNavigation.LoadHome().name) {
            HomeScreen(
                pokemonMviVm = pokemonMviVm,
                modifier = modifier,
                argument = {
                    navigateWithIntArgument(
                        navController = navController,
                        route = ConstantNavigation.LoadDetail().name,
                        argument = it
                    )
                }
            )
        }

        composable(
            "${ConstantNavigation.LoadDetail().name}/{argument}",
            arguments = listOf(navArgument("argument") {
                type = NavType.IntType
            })
        ) { entry ->
            val passedPokemonId = entry.arguments?.getInt("argument") ?: 0
            detailPokemonVm.setSelectedPokemonId(passedPokemonId)
            DetailScreen(pokemonDetailVM = detailPokemonVm)
        }

    }

}

private fun navigateWithIntArgument(
    navController: NavHostController,
    route: String,
    argument: Int
) {
    navController.navigate("$route/$argument")
}

package com.junemon.compose_stable.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.junemon.compose_stable.screen.*

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun NavigationHost(
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.LoadSplash().name,
        modifier = modifier
    ) {
        composable(ScreensNavigation.LoadSplash().name) {
            ComposeSplashScreen(navController, modifier)
        }

        composable(ScreensNavigation.LoadHome().name) {
            ComposeHomeScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
        composable(ScreensNavigation.LoadSearch().name) {
            ComposeSearchScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }

        composable(ScreensNavigation.LoadDetail().name) {
            ComposeDetailNewsScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }
}


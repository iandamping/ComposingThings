package com.junemon.compose_stable.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.screen.ComposeDetailNewsScreen
import com.junemon.compose_stable.screen.ComposeHomeScreen
import com.junemon.compose_stable.screen.ComposeSplashScreen
import com.junemon.compose_stable.screen.NewsViewModel
import timber.log.Timber

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

        composable(ScreensNavigation.LoadDetail().name) {
            ComposeDetailNewsScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }

        // composable(route ="${ScreensNavigation.LoadDetail().name}/{newsDetail}", arguments =
        // listOf(
        //     navArgument("newsDetail") {
        //         type = NavType.StringType
        //     }
        // )) { navBackStackEntry ->
        //     val newsDetailToolbarText = navBackStackEntry.arguments?.getString("newsDetail")
        //     if (!newsDetailToolbarText.isNullOrEmpty()){
        //         ComposeDetailNewsScreen(
        //             viewModel = viewModel,
        //             toolbarText = newsDetailToolbarText,
        //             navController = navController,
        //             modifier = modifier
        //         )
        //     }
        // }
    }
}


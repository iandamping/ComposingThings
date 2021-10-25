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
    sharedViewModel: ActivityRetainViewModel,
    composeViewModel: ComposableViewModel,
    homeViewModel: HomeMviViewModel,
    searchViewModel: SearchMviViewModel,
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
                sharedViewModel = sharedViewModel,
                homeViewModel = homeViewModel,
                composableViewModel = composeViewModel,
                navController = navController,
                modifier = modifier,
//                selectDetailNews = {
//                    navigateToSingleNews(navController,it)
//                }
            )
        }
        composable(ScreensNavigation.LoadSearch().name) {
            ComposeSearchScreen(
                sharedViewModel = sharedViewModel,
                searchViewModel = searchViewModel,
                composableViewModel = composeViewModel,
                navController = navController,
                modifier = modifier
            )
        }

//        composable("${ScreensNavigation.LoadDetail().name}/{newsDetail}",
//        arguments = listOf(navArgument("newsDetail"){
//            type = NavType.StringType
//        })) { entry ->
//            val passedNewsDetail = entry.arguments?.getString("newsDetail")
//            Timber.e("news  : $passedNewsDetail")
//            val newsValue =  Gson().fromJson(passedNewsDetail, News::class.java)
//            Timber.e("news passed : $newsValue")
//            ComposeDetailNewsScreen(
//                viewModel = mviViewModel,
//                newsValue = newsValue,
//                navController = navController,
//                modifier = modifier
//            )
//        }

        composable(ScreensNavigation.LoadDetail().name) {
            ComposeDetailNewsScreen(
                sharedViewModel = sharedViewModel,
                composableViewModel = composeViewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }


}

private fun navigateToSingleNews(navController: NavHostController, newsDetail: String) {
    navController.navigate("${ScreensNavigation.LoadDetail().name}/$newsDetail")
}


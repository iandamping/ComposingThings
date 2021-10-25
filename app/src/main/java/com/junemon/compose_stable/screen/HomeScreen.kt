package com.junemon.compose_stable.screen

import android.content.Intent
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.presentation.model.HomeScreenState
import com.junemon.compose_stable.navigation.ScreensNavigation

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@Composable
fun ComposeHomeScreen(
    sharedViewModel: ActivityRetainViewModel,
    homeViewModel: HomeMviViewModel,
    composableViewModel: ComposableViewModel,
    navController: NavHostController,
//    selectDetailNews: (String) ->Unit,
    modifier: Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val homeNewsFlowLifecycleAware = remember(homeViewModel.state, lifecycleOwner) {
        homeViewModel.state.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val homeNews by homeNewsFlowLifecycleAware.collectAsState(initial = HomeScreenState.initial())

    composableViewModel.NewsToolbar(screen = ScreensNavigation.LoadHome(), actionClick = {
        navController.navigate(ScreensNavigation.LoadSearch().name)
    }) {
        when {
            homeNews.isLoading -> composableViewModel.LottieCirclingLoading(200.dp, modifier)
            homeNews.failedMessage.isNotEmpty() -> composableViewModel.FailedScreen(
                text = homeNews.failedMessage,
                modifier = modifier
            )
            homeNews.data.isNotEmpty() -> composableViewModel.ListNews(
                news = homeNews.data,
                modifier = modifier,
                newsSelect = {
//                    selectDetailNews(Gson().toJson(it))
                    sharedViewModel.switchScreenNewsDetail(Gson().toJson(it))
                    navController.navigate(ScreensNavigation.LoadDetail().name)
                })
        }
    }


    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    val context = LocalContext.current

    composableViewModel.BackHandler(backDispatcher = backDispatcher) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }
}



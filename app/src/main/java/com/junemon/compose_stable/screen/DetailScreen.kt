package com.junemon.compose_stable.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.navigation.ScreensNavigation

/**
 * Created by Ian Damping on 10,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun ComposeDetailNewsScreen(
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    val news = viewModel.newsDetailFlow.collectAsState(null)
    val newsValue =  Gson().fromJson(news.value, News::class.java)

    if (!news.value.isNullOrEmpty()) {
        viewModel.NewsToolbar(
            screen = ScreensNavigation.LoadDetail(),
            toolBarText = newsValue.sourceName,
            navigationClick = { navController.navigateUp() },
            actionClick = { navController.navigateUp() }) {

            viewModel.NewsDetail(
                newsValue,
                modifier = modifier,
                navigationClick = { navController.navigateUp() },
                actionClick = { navController.navigateUp() })
        }

    }
}
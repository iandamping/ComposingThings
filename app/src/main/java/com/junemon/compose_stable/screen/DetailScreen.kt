package com.junemon.compose_stable.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.response.News

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
    if (!news.value.isNullOrEmpty()) {
        viewModel.NewsDetail(
            Gson().fromJson(news.value, News::class.java),
            modifier = modifier,
            navigationClick = { navController.navigateUp() },
            actionClick = { navController.navigateUp() })
    }
}
package com.junemon.compose_stable.screen

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.navigation.ScreensNavigation
import timber.log.Timber

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun ComposeHomeScreen(
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    viewModel.NewsToolbar(screen = ScreensNavigation.LoadHome(),actionClick = {
        Timber.e("clicked at Home")
    }) {
        when (val result: DomainResult<List<News>> = viewModel.getNews().value) {
            is DomainResult.Data -> viewModel.ListNews(
                news = result.data,
                modifier = modifier,
                newsSelect = {
                    viewModel.setNewsDetail(Gson().toJson(it))
                    navController.navigate(ScreensNavigation.LoadDetail().name)
                    // navigateToDetailNews(navController = navController, newsDetail = it.sourceName)
                })

            is DomainResult.Error -> viewModel.FailedScreen(text = result.message, modifier = modifier)

            DomainResult.Loading -> viewModel.LottieCirclingLoading(200.dp,modifier)
        }
    }



    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    // val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    viewModel.BackHandler(backDispatcher = backDispatcher) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }
}

private fun navigateToDetailNews(navController: NavHostController, newsDetail: String) {
    navController.navigate("${ScreensNavigation.LoadDetail().name}/${newsDetail}")
}

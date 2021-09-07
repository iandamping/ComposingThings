package com.junemon.compose_stable.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.presentation.common.LottieLoading
import com.junemon.compose_stable.core.presentation.screens.ListNews
import timber.log.Timber

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@Composable
fun ComposeHomeScreen(
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    val result: DomainResult<List<News>> by viewModel.getNews()
        .observeAsState(initial = DomainResult.Loading)
    when (result) {
        is DomainResult.Data -> ListNews(
            news = (result as DomainResult.Data<List<News>>).data,
            modifier = modifier,
            newsSelect = {
                Timber.e("selected news : ${it.newsAuthor}")
            })

        is DomainResult.Error -> Toast.makeText(
            LocalContext.current,
            (result as DomainResult.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        DomainResult.Loading -> LottieLoading()
    }
}

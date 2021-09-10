package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.junemon.compose_stable.core.domain.model.response.News

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ScreensUseCase {

    @ExperimentalUnitApi
    @Composable
    fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier)

    @ExperimentalUnitApi
    @Composable
    fun NewsDetail(news: News, modifier: Modifier)

    @ExperimentalUnitApi
    @Composable
    fun DefaultToolbar(navigationClick: () -> Unit, modifier: Modifier)

    @ExperimentalUnitApi
    @Composable
    fun NewsDetailToolbar(news: News, navigationClick: () -> Unit, actionClick: () -> Unit, modifier: Modifier)
}
package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.navigation.ScreensNavigation

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ScreensUseCase {

    @Composable
    fun DefaultToolbar(
        screen: ScreensNavigation,
        toolBarText: String,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    )

    @ExperimentalUnitApi
    @Composable
    fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier)

    @ExperimentalUnitApi
    @Composable
    fun NewsDetail(
        news: News,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        modifier: Modifier
    )

    @Composable
    fun FailedScreen(text:String,modifier: Modifier)

    @Composable
    fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean,
        onBack: () -> Unit
    )

    @Composable
    fun LottieCirclingLoading(size: Dp, modifier: Modifier)

    @Composable
    fun LottieFluidLoading(size: Dp, modifier: Modifier)

    @Composable
    fun Shimmer(itemSize: Int, modifier: Modifier)
}
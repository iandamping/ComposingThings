package com.junemon.compose_stable.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface LoadingUseCase {

    @Composable
    fun LottieCirclingLoading()

    @Composable
    fun LottieFluidLoading()

    @Composable
    fun Shimmer(itemSize: Int, modifier: Modifier)
}
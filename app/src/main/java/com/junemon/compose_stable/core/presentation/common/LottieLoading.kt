package com.junemon.compose_stable.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.junemon.compose_stable.util.Constant

@Composable
fun LottieLoading() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(Constant.FLUID_LOADING))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
    )

    LottieAnimation(composition, progress)
}
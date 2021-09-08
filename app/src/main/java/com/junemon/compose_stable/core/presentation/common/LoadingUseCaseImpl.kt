package com.junemon.compose_stable.core.presentation.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.junemon.compose_stable.ui.theme.ShimmerColorShades
import com.junemon.compose_stable.util.Constant
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class LoadingUseCaseImpl @Inject constructor() : LoadingUseCase {

    @Composable
    override fun LottieCirclingLoading() {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(Constant.CIRCLING_LOADING))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
        )

        LottieAnimation(composition, progress)
    }

    @Composable
    override fun LottieFluidLoading() {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(Constant.FLUID_LOADING))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
        )

        LottieAnimation(composition, progress)
    }

    @Composable
    override fun Shimmer(itemSize: Int, modifier: Modifier) {
        LazyColumn(Modifier.padding(Dp(8f))) {
            repeat(itemSize) {
                item {
                    ShimmerAnimation(modifier)
                }
            }
        }
    }

    @Composable
    private fun ShimmerAnimation(modifier: Modifier) {
        /**
        Create InfiniteTransition
        which holds child animation like [Transition]
        animations start running as soon as they enter
        the composition and do not stop unless they are removed
         */
        val transition = rememberInfiniteTransition()
        val translateAnim by transition.animateFloat(
            /**
            Specify animation positions,
            initial Values 0F means it starts from 0 position
             */
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */
                tween(durationMillis = 1200, easing = FastOutSlowInEasing),
                RepeatMode.Reverse
            )
        )

        /**
         * Create a gradient using the list of colors
         * Use Linear Gradient for animating in any direction according to requirement
         * start=specifies the position to start with in cartesian like system
         *       Offset(10f,10f) means x(10,0) , y(0,10)
         * end= Animate the end position to give the shimmer effect using the transition created above
         */
        val brush = Brush.linearGradient(
            colors = ShimmerColorShades,
            start = Offset(10f, 10f),
            end = Offset(translateAnim, translateAnim)
        )

        ShimmerItem(brush = brush, modifier = modifier)
    }

    @Composable
    private fun ShimmerItem(brush: Brush, modifier: Modifier) {

        /**
         * Column composable shaped like a rectangle,
         * set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
         * which will get animated.
         * Add few more Composable to test
         */

        Row {
            Spacer(
                modifier = modifier
                    .height(Dp(150F))
                    .width(Dp(150F))
                    .background(brush = brush)
            )
            Column {
                Spacer(
                    modifier = modifier
                        .padding(
                            bottom = Dp(8F),
                            start = Dp(8F),
                            end = Dp(8F)
                        )
                        .height(Dp(50F))
                        .fillMaxWidth()
                        .background(brush = brush)
                )

                Spacer(
                    modifier = modifier
                        .height(Dp(50F))
                        .fillMaxWidth()
                        .padding(Dp(8F))
                        .background(brush = brush)
                )
            }

        }
        Spacer(modifier = modifier.height(Dp(8f)))
    }
}
package com.junemon.compose_stable.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.junemon.compose_stable.R


@Composable
fun Header(modifier: Modifier = Modifier, headerHeight: Dp, scroll: ScrollState,) {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Box(modifier = modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            //parallax effect
            translationY = -scroll.value.toFloat() / 2f // Parallax effect
            //fade with affine function implemented
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }) {

        Image(
            painter = painterResource(id = R.drawable.bg_sunset),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // to wrap the title only
                    )
                )
        )
    }
}
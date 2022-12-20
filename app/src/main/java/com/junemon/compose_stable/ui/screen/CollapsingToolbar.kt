package com.junemon.compose_stable.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private val headerHeight = 275.dp
private val toolbarHeight = 56.dp

private val paddingMedium = 16.dp

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun CollapsingToolbar(modifier: Modifier = Modifier) {
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(modifier = modifier.fillMaxSize()) {
        Header(headerHeight = headerHeight, scroll = scroll)
        Body(modifier = Modifier.verticalScroll(scroll), headerHeight = headerHeight)
        Toolbar(scroll = scroll, headerHeightPx = headerHeightPx, toolbarHeightPx = toolbarHeightPx)
        Title(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx = toolbarHeightPx,
            headerHeight = headerHeight,
            paddingMedium = paddingMedium,
            titlePaddingStart = titlePaddingStart,
            titlePaddingEnd = titlePaddingEnd,
            toolbarHeight = toolbarHeight,
            titleFontScaleEnd = titleFontScaleEnd,
            titleFontScaleStart = titleFontScaleStart
        )

    }
}







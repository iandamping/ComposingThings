package com.junemon.compose_stable.core.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme


/**
 * Created by Ian Damping on 24,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Composable
fun ComposingWithTheme(content: @Composable () -> Unit) {
    ComposingThingsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content.invoke()
        }
    }
}


fun drawArcPath(size: Size): Path {
    return Path().apply {
        reset()

        // go from (0,0) to (width, 0)
        lineTo(size.width, 0f)

        // go from (width, 0) to (width, height)
        lineTo(size.width, size.height)

        // Draw an arch from (width, height) to (0, height)
        // starting from 0 degree to 180 degree
        arcTo(
            rect =
            Rect(
                Offset(0f, 0f),
                Size(size.width, size.height)
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 0f,
            forceMoveTo = false
        )

        // go from (0, height) to (0, 0)
        lineTo(0f, 0f)
        close()
    }
}

class CostumShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawArcPath(size = size)
        )
    }
}


package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_1
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_2
import com.junemon.compose_stable.util.TimerConstant.calculatorFontFamily


/**
 * Created by Ian Damping on 26,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Composable
fun TimerScreen(
        timeTickingForDialog: Float?,
        timeTickingForText: Long?,
        modifier: Modifier = Modifier
) {

    Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
    ) {
        timeTickingForDialog?.let { nonNullTimeTicking ->
            val animatedProgress by animateFloatAsState(
                    targetValue = nonNullTimeTicking,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            )

            CircularProgressIndicator(
                    color = LIGHT_GREEN_1,
                    modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(),
                    progress = animatedProgress,
                    strokeWidth = 12.dp
            )
            UnderlyingCircle()
        }

        timeTickingForText?.let { nonNullTimeTicking ->
            val value = DateUtils.formatElapsedTime(nonNullTimeTicking)
            Text(
                    text = value,
                    fontFamily = calculatorFontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.h5,
                    fontSize = 90.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
            )
        }

    }


}

@Composable
fun UnderlyingCircle() {
    val progress = 1.0f
    val stroke = with(LocalDensity.current) { Stroke(1.dp.toPx()) }

    Canvas(Modifier
            .progressSemantics(progress)
            .height(340.dp)
            .fillMaxWidth()
            .focusable()) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
                halfSize.width - innerRadius,
                halfSize.height - innerRadius
        )

        val diameterOffset = stroke.width / 2
        val arcDimen = size.width - 2 * diameterOffset

        val size = Size(innerRadius * 2, innerRadius * 2)
        val startAngle = 270f
        val sweep = progress * 360f

        drawArc(
                color = LIGHT_GREEN_1,
                startAngle = startAngle,
                sweepAngle = sweep,
//                topLeft = Offset(diameterOffset, diameterOffset),
//                size = Size(arcDimen, arcDimen),
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
        )
    }
}


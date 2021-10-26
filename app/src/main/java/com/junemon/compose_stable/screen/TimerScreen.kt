package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                progress = animatedProgress,
                strokeWidth = 12.dp
            )
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


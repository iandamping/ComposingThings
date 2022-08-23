package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import android.view.ContextThemeWrapper
import android.widget.NumberPicker
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.junemon.compose_stable.BasicTimerViewModel
import com.junemon.compose_stable.R
import com.junemon.compose_stable.ui.theme.CalculatorFontFamily
import com.junemon.compose_stable.util.TimerConstant

@Composable
fun BasicTimerScreen(modifier: Modifier = Modifier, timerVm: BasicTimerViewModel) {
    val timeTickingForText by timerVm.currentTime.collectAsState()
    val timeTickingForDialog by timerVm.currentTimeInFloat.collectAsState()
    val isRunning by timerVm.isTimerRunning.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isRunning) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(350.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularTimer(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    timeTickingForDialog = timeTickingForDialog
                )
                TimerText(
                    modifier = Modifier.align(Alignment.Center),
                    timeTickingForText = timeTickingForText
                )
            }
        } else {
            SelectingHourMinuteSecondScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(350.dp),
                timerVm = timerVm
            )
        }

        BasicTimerButton(timerVm = timerVm)
    }
}


@Composable
private fun TimerText(modifier: Modifier = Modifier, timeTickingForText: Long?) {
    val value = DateUtils.formatElapsedTime(timeTickingForText ?: 0L)
    Text(
        text = value,
        fontFamily = CalculatorFontFamily,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.h1,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
private fun CircularTimer(modifier: Modifier = Modifier, timeTickingForDialog: Float?) {
    val animatedProgress by animateFloatAsState(
        targetValue = timeTickingForDialog ?: 0.0f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    UnderlyingCircle()

    CircularProgressIndicator(
        color = TimerConstant.LIGHT_GREEN_1,
        modifier = modifier,
        progress = animatedProgress,
        strokeWidth = 12.dp
    )
}

@Composable
fun UnderlyingCircle() {
    val progress = 1.0f
    val stroke = with(LocalDensity.current) { Stroke(1.dp.toPx()) }

    Canvas(
        Modifier
            .progressSemantics(progress)
            .fillMaxSize()
            .focusable()
    ) {
        val diameterOffset = stroke.width / 2
        val arcDimen = size.width - 2 * diameterOffset
        val startAngle = 270f
        val sweep = progress * 360f

        drawArc(
            color = TimerConstant.LIGHT_GREEN_2,
            startAngle = startAngle,
            sweepAngle = sweep,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimen, arcDimen),
            useCenter = false,
            style = stroke
        )
    }
}


@Composable
fun SelectingHourMinuteSecondScreen(
    modifier: Modifier = Modifier,
    timerVm: BasicTimerViewModel
) {
    val hourArray = stringArrayResource(id = R.array.arr_hours)
    val minuteArray = stringArrayResource(id = R.array.arr_minutes)
    val secondArray = stringArrayResource(id = R.array.arr_seconds)
    val pickerTheme = R.style.AppTheme_Picker
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.hour), color = Color.White)
            AndroidView(factory = {
                NumberPicker(ContextThemeWrapper(it, pickerTheme)).apply {
                    minValue = TimerConstant.DEFAULT_INTEGER_VALUE
                    maxValue = hourArray.size - 1
                    displayedValues = hourArray
                    setOnValueChangedListener { _, _, newVal ->
                        timerVm.setHour(newVal)
                    }
                }
            })
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.minute), color = Color.White)

            AndroidView(factory = {
                NumberPicker(ContextThemeWrapper(it, pickerTheme)).apply {

                    minValue = TimerConstant.DEFAULT_INTEGER_VALUE
                    maxValue = minuteArray.size - 1
                    displayedValues = minuteArray
                    setOnValueChangedListener { _, _, newVal ->
                        timerVm.setMinute(newVal)

                    }
                }
            })
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.second), color = Color.White)

            AndroidView(factory = {
                NumberPicker(ContextThemeWrapper(it, pickerTheme)).apply {

                    minValue = TimerConstant.DEFAULT_INTEGER_VALUE
                    maxValue = secondArray.size - 1
                    displayedValues = secondArray
                    setOnValueChangedListener { _, _, newVal ->
                        timerVm.setSecond(newVal)
                    }
                }
            })
        }
    }
}

@Composable
fun BasicTimerButton(modifier: Modifier = Modifier, timerVm: BasicTimerViewModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { timerVm.startCounting() }) {
            Text(text = stringResource(id = R.string.start))
        }

        Button(onClick = { timerVm.pauseTimer() }) {
            Text(text = stringResource(id = R.string.pause))
        }
        Button(onClick = { timerVm.cancelAllTimer() }) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}

package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import android.widget.NumberPicker
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.junemon.compose_stable.BoxingViewModel
import com.junemon.compose_stable.R
import com.junemon.compose_stable.RestTime
import com.junemon.compose_stable.ui.theme.CalculatorFontFamily
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_INTEGER_VALUE
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_1
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_2


/**
 * Created by Ian Damping on 26,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    intervalVm: BoxingViewModel,
) {

    val timeTickingForText by intervalVm.currentTime.collectAsState()
    val timeTickingForDialog by intervalVm.currentTimeInFloat.observeAsState()

    Box(
        modifier = modifier,
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
        color = LIGHT_GREEN_1,
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
            color = LIGHT_GREEN_2,
            startAngle = startAngle,
            sweepAngle = sweep,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimen, arcDimen),
//                topLeft = topLeft,
//                size = size,
            useCenter = false,
            style = stroke
        )
    }
}

@Composable
fun WaringTimeRadioButton(
    modifier: Modifier = Modifier,
    intervalVm: BoxingViewModel
) {
    val listOfRestTime = listOf(
        RestTime(stringResource(id = R.string.off), 0),
        RestTime(stringResource(id = R.string.ten_second), 10),
        RestTime(stringResource(id = R.string.thirty_sec), 30),
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(listOfRestTime[0]) }

    val isRadioButtonEnabled by intervalVm.isTimerRunning.observeAsState(initial = false)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Text(
            text = stringResource(id = R.string.warning),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 8.dp)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOfRestTime.forEach { text ->
                Row(
                    Modifier
                        .wrapContentWidth()
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                intervalVm.setWarningValue(text.time)
                            },
                            enabled = !isRadioButtonEnabled
                        )
                        // below line is use to add
                        // padding to radio button.
                        .padding(horizontal = 4.dp)

                ) {
                    // below line is use to
                    // generate radio button
                    RadioButton(
                        // inside this method we are
                        // adding selected with a option.
                        selected = (text == selectedOption),
                        onClick = {
                            // inide on click method we are setting a
                            // selected option of our radio buttons.
                            onOptionSelected(text)
                            intervalVm.setWarningValue(text.time)
                        },
                        enabled = !isRadioButtonEnabled
                    )
                    // below line is use to add
                    // text to our radio buttons.
                    Text(
                        text = text.name,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun SelectingRestRoundTimeAndRoundScreen(
    modifier: Modifier = Modifier,
    intervalVm: BoxingViewModel
) {
    val restArray = stringArrayResource(id = R.array.arr_rest_time)
    val roundTimeArray = stringArrayResource(id = R.array.arr_round_time)
    val roundsArray = stringArrayResource(id = R.array.arr_rounds)
    val pickerTheme = R.style.AppTheme_Picker
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.rest), color = Color.White)
            AndroidView(factory = {
                NumberPicker(android.view.ContextThemeWrapper(it, pickerTheme)).apply {
                    if (value == DEFAULT_INTEGER_VALUE) {
                        intervalVm.setRestTime(TimerConstant.setCustomTime(0))
                    }
                    minValue = DEFAULT_INTEGER_VALUE
                    maxValue = restArray.size - 1
                    displayedValues = restArray
                    setOnValueChangedListener { _, _, newVal ->
                        when (newVal) {
                            0 -> intervalVm.setRestTime(TimerConstant.setCustomTime(0))
                            1 -> intervalVm.setRestTime(TimerConstant.setCustomTime(15))
                            2 -> intervalVm.setRestTime(TimerConstant.setCustomTime(30))
                            3 -> intervalVm.setRestTime(TimerConstant.setCustomTime(60))
                            4 -> intervalVm.setRestTime(TimerConstant.setCustomTime(90))
                            5 -> intervalVm.setRestTime(TimerConstant.setCustomTime(120))
                            6 -> intervalVm.setRestTime(TimerConstant.setCustomTime(150))
                            7 -> intervalVm.setRestTime(TimerConstant.setCustomTime(180))
                        }
                    }
                }
            })
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.round_time), color = Color.White)

            AndroidView(factory = {
                NumberPicker(android.view.ContextThemeWrapper(it, pickerTheme)).apply {
                    intervalVm.setRoundTime(value)
                    minValue = DEFAULT_INTEGER_VALUE
                    maxValue = roundTimeArray.size - 1
                    displayedValues = roundTimeArray
                    setOnValueChangedListener { _, _, newVal ->
                        intervalVm.setRoundTime(newVal)
                    }
                }
            })
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.rounds), color = Color.White)

            AndroidView(factory = {
                NumberPicker(android.view.ContextThemeWrapper(it, pickerTheme)).apply {
                    intervalVm.setWhichRound(value + 1)
                    minValue = DEFAULT_INTEGER_VALUE
                    maxValue = roundsArray.size - 1
                    displayedValues = roundsArray
                    setOnValueChangedListener { _, _, newVal ->
                        intervalVm.setWhichRound(newVal + 1)
                    }
                }
            })
        }
    }
}

@Composable
fun IntervalTimerButton(modifier: Modifier = Modifier, intervalVm: BoxingViewModel) {
    val state by intervalVm.isTimerRunning.observeAsState(initial = false)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { intervalVm.startCounting() }, enabled = !state) {
            Text(text = stringResource(id = R.string.start))
        }

        Button(onClick = { intervalVm.cancelAllTimer() }, enabled = state) {
            Text(text = stringResource(id = R.string.stop))
        }

        Button(onClick = { intervalVm.resetAll() }) {
            Text(text = stringResource(id = R.string.reset))
        }


    }
}


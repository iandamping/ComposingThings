package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.junemon.compose_stable.IntervalTimerViewModel
import com.junemon.compose_stable.R
import com.junemon.compose_stable.RestTime
import com.junemon.compose_stable.ui.theme.CalculatorFontFamily
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_INTEGER_VALUE


/**
 * Created by Ian Damping on 26,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Composable
fun IntervalTimerScreen(
    modifier: Modifier = Modifier,
    intervalVm: IntervalTimerViewModel,
) {
    val isTimerRunning by intervalVm.isTimerRunning.observeAsState(initial = false)

    Column(modifier = modifier.fillMaxSize()) {
        if (isTimerRunning) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(350.dp),
                contentAlignment = Alignment.Center
            ) {
                TimerText(
                    intervalVm = intervalVm
                )
            }
        } else {
            SelectingRestRoundTimeAndRoundScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(350.dp),
                intervalVm = intervalVm
            )
        }
        WaringTimeRadioButton(intervalVm = intervalVm)
        IntervalTimerButton(intervalVm = intervalVm)
    }
}

@Composable
private fun TimerText(modifier: Modifier = Modifier, intervalVm: IntervalTimerViewModel) {
    val isResting by intervalVm.isResting.observeAsState(initial = false)
    val timeTickingForText by intervalVm.currentTime.collectAsState()
    val currentRound by intervalVm.roundCounter.observeAsState(initial = TimerConstant.DEFAULT_ROUND_COUNTER_VALUE)
    val whichRoundRunning by intervalVm.whichRoundValue.observeAsState(initial = TimerConstant.DEFAULT_ROUND_COUNTER_VALUE)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.current_round, currentRound, whichRoundRunning),
            fontFamily = CalculatorFontFamily,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = DateUtils.formatElapsedTime(timeTickingForText ?: 0L),
            fontFamily = CalculatorFontFamily,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        if (isResting) {
            Text(
                text = stringResource(id = R.string.rest),
                fontFamily = CalculatorFontFamily,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

}

@Composable
fun WaringTimeRadioButton(
    modifier: Modifier = Modifier,
    intervalVm: IntervalTimerViewModel
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
    intervalVm: IntervalTimerViewModel
) {
    val restArray = stringArrayResource(id = R.array.arr_rest_time)
    val roundTimeArray = stringArrayResource(id = R.array.arr_round_time)
    val roundsArray = stringArrayResource(id = R.array.arr_rounds)
    val pickerTheme = R.style.AppTheme_Picker
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.rest), color = Color.White)
            AndroidView(factory = {
                NumberPicker(android.view.ContextThemeWrapper(it, pickerTheme)).apply {
                    if (value == DEFAULT_INTEGER_VALUE) {
                        intervalVm.setRestTime(DEFAULT_INTEGER_VALUE)
                    }
                    minValue = DEFAULT_INTEGER_VALUE
                    maxValue = restArray.size - 1
                    displayedValues = restArray
                    setOnValueChangedListener { _, _, newVal ->
                        intervalVm.setRestTime(newVal)
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
fun IntervalTimerButton(modifier: Modifier = Modifier, intervalVm: IntervalTimerViewModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { intervalVm.startCounting() }) {
            Text(text = stringResource(id = R.string.start))
        }

        Button(onClick = { intervalVm.cancelAllTimer() }) {
            Text(text = stringResource(id = R.string.stop))
        }

        Button(onClick = { intervalVm.resetAll() }) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}


package com.junemon.compose_stable.screen

import android.text.format.DateUtils
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.junemon.compose_stable.BoxingViewModel
import com.junemon.compose_stable.RestTime
import com.junemon.compose_stable.ui.theme.CalculatorFontFamily
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_1
import com.junemon.compose_stable.util.TimerConstant.LIGHT_GREEN_2
import timber.log.Timber


/**
 * Created by Ian Damping on 26,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Composable
fun TimerScreen(
    timerViewModel: BoxingViewModel,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val timeTickingLifecycle = remember(timerViewModel.currentTime, lifecycleOwner) {
        timerViewModel.currentTime.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val timeTickingInFloatLifecycle =
        remember(timerViewModel.currentTimeInFloat, lifecycleOwner) {
            timerViewModel.currentTimeInFloat.flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
        }

    val timeTickingForText by timeTickingLifecycle.collectAsState(null)
    val timeTickingForDialog by timeTickingInFloatLifecycle.collectAsState(null)

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
fun RestTimeRadioButton(modifier: Modifier = Modifier, itemSelected: (Int) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = "Pick how long to rest",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 8.dp)
        )
        SimpleRadioButtonComponent(item = TimerConstant.listOfRestTime) {
            itemSelected.invoke(it)
        }
    }
}

@Composable
fun SimpleRadioButtonComponent(
    modifier: Modifier = Modifier,
    item: List<RestTime>,
    itemSelected: (Int) -> Unit
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(item[0]) }
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .horizontalScroll(state),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item.forEach { text ->
            Row(
                Modifier
                    .wrapContentWidth()
                    .selectable(
                        // this method is called when
                        // radio button is selected.
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            itemSelected(text.time)
                        }
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
                        itemSelected(text.time)
                    }
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

@Composable
fun StartTimerButton(buttonClicked: () -> Unit) {
    Button(onClick = buttonClicked) {
        Text(text = "Start")
    }
}

@Composable
fun PauseTimerButton(buttonClicked: () -> Unit) {
    Button(onClick = buttonClicked) {
        Text(text = "Pause")
    }
}

@Composable
fun ResetTimerButton(buttonClicked: () -> Unit) {
    Button(onClick = buttonClicked) {
        Text(text = "Reset")
    }
}


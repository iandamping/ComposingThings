package com.junemon.compose_stable.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.R
import com.junemon.compose_stable.StopWatchViewModel
import com.junemon.compose_stable.ui.theme.CalculatorFontFamily

@Composable
fun StopwatchTimerScreen(modifier: Modifier = Modifier, stopwatchVm: StopWatchViewModel) {
    Column(modifier = modifier.fillMaxSize()) {
        StopwatchTimerText(
            stopwatchVm = stopwatchVm
        )
        StopwatchTimerButton(stopwatchVm = stopwatchVm)
    }
}


@Composable
private fun StopwatchTimerText(modifier: Modifier = Modifier, stopwatchVm: StopWatchViewModel) {
    val timeTickingForText by stopwatchVm.stopwatchTimer.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(350.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = timeTickingForText,
            fontFamily = CalculatorFontFamily,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.Center)
        )
    }

}

@Composable
fun StopwatchTimerButton(modifier: Modifier = Modifier, stopwatchVm: StopWatchViewModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { stopwatchVm.start() }) {
            Text(text = stringResource(id = R.string.start))
        }

        Button(onClick = { stopwatchVm.pause() }) {
            Text(text = stringResource(id = R.string.pause))
        }

        Button(onClick = { stopwatchVm.stop() }) {
            Text(text = stringResource(id = R.string.stop))
        }
    }
}

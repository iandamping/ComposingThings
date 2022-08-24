package com.junemon.compose_stable

import androidx.lifecycle.ViewModel
import com.junemon.compose_stable.util.stopwatch.StopwatchListOrchestrator
import com.permatabank.qram.core.data.datasource.cache.preference.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StopWatchViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) :
    ViewModel() {

    val stopwatchTimer: StateFlow<String> = stopwatchListOrchestrator.ticker

    val isTimerRunning: StateFlow<Boolean> = stopwatchListOrchestrator.isTimerRunning

    fun start() {
        stopwatchListOrchestrator.start()
    }

    fun stop() {
        stopwatchListOrchestrator.stop()
    }

    fun pause() {
        stopwatchListOrchestrator.pause()
    }

    fun lap() {
        Timber.e("value : ${stopwatchTimer.value}")
    }


}
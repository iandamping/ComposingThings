package com.junemon.compose_stable.util.stopwatch

import com.junemon.compose_stable.di.dispatcher.DefaultApplicationCoroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class StopwatchListOrchestratorImpl @Inject constructor(
    private val stopwatchStateHolder: StopwatchStateHolder,
    @DefaultApplicationCoroutineScope private val scope: CoroutineScope,
) : StopwatchListOrchestrator {
    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("00:00:000")
    private val mutableIsTimerRunning = MutableStateFlow(false)

    override val isTimerRunning: StateFlow<Boolean>
        get() = mutableIsTimerRunning.asStateFlow()

    override val ticker: StateFlow<String> = mutableTicker.asStateFlow()

    override fun start() {
        if (job == null) startJob()
        mutableIsTimerRunning.value = true
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    override fun pause() {
        mutableIsTimerRunning.value = false
        stopwatchStateHolder.pause()
        stopJob()
    }

    override fun stop() {
        mutableIsTimerRunning.value = false
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = "00:00:000"
    }
}
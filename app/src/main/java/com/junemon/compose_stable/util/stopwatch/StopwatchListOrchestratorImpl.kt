package com.junemon.compose_stable.util.stopwatch

import com.junemon.compose_stable.di.dispatcher.BackgroundDispatcherQualifier
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
    private val mutableTicker = MutableStateFlow("")


    override val ticker: StateFlow<String> = mutableTicker.asStateFlow()

    override fun start() {
        if (job == null) startJob()
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
        stopwatchStateHolder.pause()
        stopJob()
    }

    override fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = ""
    }
}
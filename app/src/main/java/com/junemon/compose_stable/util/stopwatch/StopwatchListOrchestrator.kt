package com.junemon.compose_stable.util.stopwatch

import kotlinx.coroutines.flow.StateFlow

interface StopwatchListOrchestrator {

    val ticker: StateFlow<String>

    val isTimerRunning: StateFlow<Boolean>

    fun start()

    fun pause()

    fun stop()
}
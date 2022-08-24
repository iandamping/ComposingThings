package com.junemon.compose_stable.util.stopwatch

sealed class StopwatchState {
    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : StopwatchState()
    data class Paused(
        val elapsedTime: Long
    ) : StopwatchState()
}
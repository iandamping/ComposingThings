package com.junemon.compose_stable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.repository.StopwatchRepository
import com.junemon.compose_stable.util.stopwatch.StopwatchListOrchestrator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopWatchViewModel @Inject constructor(
    private val repository: StopwatchRepository,
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) :
    ViewModel() {

    val stopwatchTimer: StateFlow<String> = stopwatchListOrchestrator.ticker

    val isTimerRunning: StateFlow<Boolean> = stopwatchListOrchestrator.isTimerRunning

    val lapTimeList: StateFlow<List<String>> = repository.loadLapTime().stateIn(
        initialValue = emptyList(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

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
        viewModelScope.launch {
            repository.insertLapTime(stopwatchTimer.value)
        }
    }

    fun deleteAllLapItem(){
        viewModelScope.launch {
            repository.deleteAllLapTime()
        }
    }


}
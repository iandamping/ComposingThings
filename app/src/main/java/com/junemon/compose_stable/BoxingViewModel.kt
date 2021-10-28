package com.junemon.compose_stable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DONE
import com.junemon.compose_stable.util.TimerConstant.DONE_FLOAT
import com.junemon.compose_stable.util.TimerConstant.ONE_SECOND
import com.junemon.compose_stable.util.TimerConstant.setCustomFloat
import com.junemon.compose_stable.util.timer.BoxingTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


/**
 * Created by Ian Damping on 25,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class BoxingViewModel @Inject constructor(private val boxingTimer: BoxingTimer) : ViewModel() {
    private val _isTimerRunning: MutableLiveData<Boolean> = MutableLiveData()
    private val _loopingCounterValue: MutableLiveData<Int> = MutableLiveData()
    private val _currentTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _restTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _pausedTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _currentTimeInFloat: MutableStateFlow<Float?> = MutableStateFlow(null)
    private val _timerState: MutableStateFlow<TimerState> = MutableStateFlow(TimerState.RoundTime)

    val loopingCounterValue: LiveData<Int>
        get() = _loopingCounterValue

    val pausedTime: StateFlow<Long?>
        get() = _pausedTime

    val restTime: StateFlow<Long?>
        get() = _restTime

    val currentTime: StateFlow<Long?>
        get() = _currentTime

    val currentTimeInFloat: StateFlow<Float?>
        get() = _currentTimeInFloat

    val isTimerRunning: MutableLiveData<Boolean>
        get() = _isTimerRunning

    val timerState: StateFlow<TimerState>
        get() = _timerState

    fun setTimerIsRunning(data: Boolean) {
        _isTimerRunning.value = data
    }

    fun setLoopingCounterRound(data: Int) {
        _loopingCounterValue.value = data
    }

    fun setTimerState(state: TimerState) {
        _timerState.value = state
    }

    fun setRestTime(data: Int) {
        _restTime.value = when (data) {
            3 -> TimerConstant.setCustomMinutes(3)
            5 -> TimerConstant.setCustomMinutes(5)
            else -> TimerConstant.setCustomMinutes(10)
        }
    }

    fun startTimer(durationTime: Long, finishTicking: () -> Unit = {}) {
        boxingTimer.startTimer(durationTime = durationTime,
            onFinish = {
                _currentTime.value = DONE
                _pausedTime.value = DONE
                _currentTimeInFloat.value = DONE_FLOAT
                finishTicking.invoke()
            }, onTicking = { millisUntilFinished ->
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
                _pausedTime.value = (millisUntilFinished / ONE_SECOND)
                _currentTimeInFloat.value =
                    setCustomFloat(durationTime, (millisUntilFinished / ONE_SECOND))
            })
    }


    fun pauseTimer() {
        boxingTimer.stopTimer()
    }

    fun cancelAllTimer() {
        boxingTimer.stopTimer()
        _currentTime.value = null
        _pausedTime.value = null
        _currentTimeInFloat.value = DONE_FLOAT
    }

}
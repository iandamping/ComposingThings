package com.junemon.compose_stable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_POMODORO_ROUND
import com.junemon.compose_stable.util.TimerConstant.DONE
import com.junemon.compose_stable.util.TimerConstant.DONE_FLOAT
import com.junemon.compose_stable.util.TimerConstant.MAX_POMODORO_ROUND
import com.junemon.compose_stable.util.TimerConstant.ONE_SECOND
import com.junemon.compose_stable.util.TimerConstant.setCustomFloat
import com.junemon.compose_stable.util.ringer.BellRinger
import com.junemon.compose_stable.util.timer.BoxingTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 25,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class BoxingViewModel @Inject constructor(
    private val boxingTimer: BoxingTimer,
    private val bellRinger: BellRinger
) : ViewModel() {

    private var pomodoroRound = DEFAULT_POMODORO_ROUND
    private val _isTimerRunning: MutableLiveData<Boolean> = MutableLiveData()
    private val _currentPomodoroRound: MutableStateFlow<Int> =
        MutableStateFlow(DEFAULT_POMODORO_ROUND)
    private val _currentTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _restTime: MutableStateFlow<Int> = MutableStateFlow(3)
    private val _pausedTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _currentTimeInFloat: MutableStateFlow<Float?> = MutableStateFlow(null)
    private val _timerState: MutableStateFlow<TimerState> =
        MutableStateFlow(TimerState.RoundTime(TimerConstant.setCustomMinutes(25)))

    init {
        viewModelScope.launch {
            currentPomodoroRound.collect {
                if (it == MAX_POMODORO_ROUND) {
                    Timber.e("Done run sequential")
                    pomodoroRound = DEFAULT_POMODORO_ROUND
                    _currentPomodoroRound.value = pomodoroRound
                    setTimerIsRunning(false)
                    cancelAllTimer()
                }
            }
        }
    }


    val currentPomodoroRound: StateFlow<Int>
        get() = _currentPomodoroRound

    val pausedTime: StateFlow<Long?>
        get() = _pausedTime

    val restTime: StateFlow<Int>
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

    fun setPauseTime(data: Long) {
        _pausedTime.value = data
    }

    fun incrementPomodoroRound() {
        _currentPomodoroRound.value = pomodoroRound++
    }

    fun setRoundTimeState(time: Int) {
        _timerState.value = TimerState.RoundTime(time = TimerConstant.setCustomMinutes(time))
    }

    fun setRestTimeState(time: Int) {
        _timerState.value = TimerState.RestTime(time = TimerConstant.setCustomMinutes(time))
    }

    fun setRestTime(data: Int) {
        _restTime.value = data
    }

    fun startTimer(
        durationTime: Long,
        durationTimes: Long? = null,
        finishTicking: () -> Unit = {}
    ) {
        boxingTimer.startTimer(durationTime = durationTime,
            onFinish = {
                _currentTime.value = DONE
                _pausedTime.value = null
                _currentTimeInFloat.value = DONE_FLOAT
                finishTicking.invoke()
            }, onTicking = { millisUntilFinished ->
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
                _currentTimeInFloat.value = if (durationTimes != null) {
                    setCustomFloat(durationTimes, (millisUntilFinished / ONE_SECOND))
                } else {
                    setCustomFloat(durationTime, (millisUntilFinished / ONE_SECOND))
                }
            })
    }


    fun pauseTimer(data: Long) {
        setPauseTime(data)
        boxingTimer.stopTimer()
    }

    fun cancelAllTimer() {
        boxingTimer.stopTimer()
        _currentTime.value = null
        _pausedTime.value = null
        _currentTimeInFloat.value = DONE_FLOAT
    }

    fun startRoundSound() {
        bellRinger.startBellSound()
    }

    fun endRoundSound() {
        bellRinger.endBellSound()
    }

    fun warningRoundSound() {
        bellRinger.warningBellSound()
    }

}
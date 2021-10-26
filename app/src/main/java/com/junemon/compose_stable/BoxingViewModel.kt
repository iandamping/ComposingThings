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
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 25,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class BoxingViewModel @Inject constructor(private val boxingTimer: BoxingTimer) : ViewModel() {
    private val _roundTimeValue: MutableLiveData<Long> = MutableLiveData()
    private val _currentTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _currentTimeInFloat: MutableStateFlow<Float?> = MutableStateFlow(null)

    val currentTime: StateFlow<Long?>
        get() = _currentTime

    val currentTimeInFloat: StateFlow<Float?>
        get() = _currentTimeInFloat

    val roundTimeValue: LiveData<Long>
        get() = _roundTimeValue


    fun setRoundTime(data: Int) {
        _roundTimeValue.value = when (data) {
            0 -> TimerConstant.setCustomTime(30)
            1 -> TimerConstant.setCustomMinutes(1)
            2 -> TimerConstant.setCustomMinutes(2)
            3 -> TimerConstant.setCustomMinutes(3)
            4 -> TimerConstant.setCustomMinutes(4)
            5 -> TimerConstant.setCustomMinutes(5)
            6 -> TimerConstant.setCustomMinutes(6)
            7 -> TimerConstant.setCustomMinutes(7)
            8 -> TimerConstant.setCustomMinutes(8)
            9 -> TimerConstant.setCustomMinutes(9)
            else -> TimerConstant.setCustomMinutes(10)
        }
    }

    fun startTimer(durationTime: Long, finishTicking: () -> Unit) {
        boxingTimer.startTimer(durationTime = durationTime,
            onFinish = {
                _currentTime.value = DONE
                _currentTimeInFloat.value = DONE_FLOAT
                finishTicking.invoke()
            }, onTicking = { millisUntilFinished ->
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
                _currentTimeInFloat.value = setCustomFloat(durationTime,(millisUntilFinished / ONE_SECOND))
            })
    }

    fun cancelAllTimer() {
        boxingTimer.stopTimer()
    }

}
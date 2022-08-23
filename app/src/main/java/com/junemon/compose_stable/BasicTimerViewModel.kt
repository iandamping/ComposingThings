package com.junemon.compose_stable

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.base.BaseViewModel
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_INTEGER_VALUE
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_LONG_VALUE
import com.junemon.compose_stable.util.TimerConstant.setCustomHour
import com.junemon.compose_stable.util.TimerConstant.setCustomMinutes
import com.junemon.compose_stable.util.TimerConstant.setCustomSeconds
import com.junemon.compose_stable.util.timer.TimerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BasicTimerViewModel @Inject constructor(private val timerHelper: TimerHelper) :
    BaseViewModel() {
    private val _isTimerRunning: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _pausedTime: MutableLiveData<Long> = MutableLiveData(DEFAULT_LONG_VALUE)
    private val _currentTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _currentTimeInFloat: MutableStateFlow<Float?> = MutableStateFlow(null)
    private val _hour: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)
    private val _minute: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)
    private val _second: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)

    val pausedTime: LiveData<Long>
        get() = _pausedTime

    val currentTime: StateFlow<Long?>
        get() = _currentTime.asStateFlow()

    val currentTimeInFloat: StateFlow<Float?>
        get() = _currentTimeInFloat.asStateFlow()

    val isTimerRunning: LiveData<Boolean>
        get() = _isTimerRunning

    private val hour: LiveData<Int>
        get() = _hour

    private val minute: LiveData<Int>
        get() = _minute

    private val second: LiveData<Int>
        get() = _second


    fun setHour(data: Int) {
        _hour.value = data
    }

    fun setMinute(data: Int) {
        _minute.value = data
    }

    fun setSecond(data: Int) {
        _second.value = data
    }

    private fun hourMapper(data: Int?): Long {
        return if (data != null) {
            setCustomHour(data)
        } else {
            setCustomHour(0)
        }
    }

    private fun minuteMapper(data: Int?): Long {
        return if (data != null) {
            setCustomMinutes(data)
        } else {
            setCustomMinutes(0)
        }
    }

    private fun secondMapper(data: Int?): Long {
        return if (data != null) {
            setCustomSeconds(data)
        } else {
            setCustomSeconds(0)
        }
    }

    private fun setTimerIsRunning(data: Boolean) {
        _isTimerRunning.value = data
    }

    private fun startTimer(
        durationTime: Long,
        durationTimes: Long? = null,
        finishTicking: () -> Unit = {}
    ) {
        setTimerIsRunning(true)
        timerHelper.startTimer(durationTime = durationTime,
            onFinish = {
                setTimerIsRunning(false)
                _currentTime.value = DEFAULT_LONG_VALUE
                _pausedTime.value = DEFAULT_LONG_VALUE
                _currentTimeInFloat.value = TimerConstant.DONE_FLOAT
                finishTicking.invoke()
            }, onTicking = { millisUntilFinished ->
                _currentTime.value = (millisUntilFinished / TimerConstant.ONE_SECOND)
                _pausedTime.value = (millisUntilFinished / TimerConstant.ONE_SECOND)
                viewModelScope.launch {
                    _currentTimeInFloat.value = TimerConstant.setMapTimeToFloat(
                        data = durationTimes,
                        ticking = (millisUntilFinished / TimerConstant.ONE_SECOND)
                    )
                }
            })

    }

    fun startCounting() {
        try {
            if (checkNotNull(pausedTime.value) != DEFAULT_LONG_VALUE) {
                val value = DateUtils.formatElapsedTime(pausedTime.value!!)
                val strParts: List<String> = value.split(":")

                if (strParts.size < 3) {
                    val minute = strParts[0].toInt()
                    val second = strParts[1].toInt()
                    if (minute != 0) {


                        startTimer(
                            durationTime = (minuteMapper(minute) + secondMapper(second)),
                            durationTimes = (setCustomMinutes(minute) + setCustomSeconds(second))
                        ) {
                            cancelAllTimer()
                        }
                    } else {


                        startTimer(
                            durationTime = secondMapper(second),
                            durationTimes = setCustomSeconds(second)
                        ) {
                            cancelAllTimer()
                        }
                    }
                } else {
                    val hour = strParts[0].toInt()
                    val minute = strParts[1].toInt()
                    val second = strParts[2].toInt()
                    startTimer(
                        durationTime = (hourMapper(hour) + minuteMapper(minute) + secondMapper(
                            second
                        )),
                        durationTimes = (setCustomHour(hour) + setCustomMinutes(minute) + setCustomSeconds(
                            second
                        ))
                    ) {
                        cancelAllTimer()
                    }
                }
            } else {
                when {
                    checkNotNull(hour.value) == 0 && checkNotNull(minute.value) == 0 && checkNotNull(
                        second.value
                    ) != 0 -> {
                        startTimer(
                            durationTime = secondMapper(checkNotNull(second.value)),
                            durationTimes = setCustomSeconds(checkNotNull(second.value))
                        ) {
                            cancelAllTimer()
                        }
                        //second ada isi nya
                    }
                    checkNotNull(hour.value) == 0 && checkNotNull(minute.value) != 0 && checkNotNull(
                        second.value
                    ) == 0 -> {
                        startTimer(
                            durationTime = minuteMapper(checkNotNull(minute.value)),
                            durationTimes = setCustomMinutes(checkNotNull(minute.value))
                        ) {
                            cancelAllTimer()
                        }
                        //minute ada isi nya
                    }
                    checkNotNull(hour.value) != 0 && checkNotNull(minute.value) == 0 && checkNotNull(
                        second.value
                    ) == 0 -> {
                        startTimer(
                            durationTime = hourMapper(checkNotNull(hour.value)),
                            durationTimes = setCustomHour(checkNotNull(hour.value))
                        ) {
                            cancelAllTimer()
                        }
                        //hour ada isi nya
                    }
                    checkNotNull(hour.value) == 0 && checkNotNull(minute.value) != 0 && checkNotNull(
                        second.value
                    ) != 0 -> {
                        val minuteDuration = minuteMapper(checkNotNull(minute.value))
                        val secondDuration = secondMapper(checkNotNull(second.value))
                        startTimer(
                            durationTime = minuteDuration + secondDuration,
                            durationTimes = (setCustomMinutes(checkNotNull(minute.value)) + setCustomSeconds(
                                checkNotNull(second.value)
                            ))
                        ) {
                            cancelAllTimer()
                        }
                        // minute & second ada isi nya
                    }
                    checkNotNull(hour.value) != 0 && checkNotNull(minute.value) == 0 && checkNotNull(
                        second.value
                    ) != 0 -> {
                        val hourDuration = hourMapper(checkNotNull(hour.value))
                        val secondDuration = secondMapper(checkNotNull(second.value))
                        startTimer(
                            durationTime = hourDuration + secondDuration,
                            durationTimes = (setCustomHour(checkNotNull(hour.value)) + setCustomSeconds(
                                checkNotNull(second.value)
                            ))
                        ) {
                            cancelAllTimer()
                        }
                        // hour & second ada isi nya
                    }
                }

            }
        } catch (e: Exception) {
            Timber.e("error : $e")
        }
    }


    fun pauseTimer() {
        timerHelper.stopTimer()
    }

    fun cancelAllTimer() {
        timerHelper.stopTimer()
        _currentTime.value = null
        _currentTimeInFloat.value = null
        _pausedTime.value = DEFAULT_LONG_VALUE
        _hour.value = DEFAULT_INTEGER_VALUE
        _minute.value = DEFAULT_INTEGER_VALUE
        _second.value = DEFAULT_INTEGER_VALUE
        setTimerIsRunning(false)
    }


    override fun onCleared() {
        super.onCleared()
        cancelAllTimer()
    }

}
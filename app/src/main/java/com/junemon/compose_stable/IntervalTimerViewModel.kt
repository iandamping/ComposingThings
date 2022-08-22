package com.junemon.compose_stable

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.util.GenericTriple
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_INTEGER_VALUE
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_LONG_VALUE
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_ROUND_COUNTER_VALUE
import com.junemon.compose_stable.util.TimerConstant.DEFAULT_WHICH_ROUND_COUNTER_VALUE
import com.junemon.compose_stable.util.TimerConstant.DONE
import com.junemon.compose_stable.util.TimerConstant.ONE_SECOND
import com.junemon.compose_stable.util.TimerConstant.REST_TIME_STATE
import com.junemon.compose_stable.util.TimerConstant.ROUND_TIME_STATE
import com.junemon.compose_stable.util.ringer.BellRinger
import com.junemon.compose_stable.util.timer.TimerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 25,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class IntervalTimerViewModel @Inject constructor(
    private val timerHelper: TimerHelper,
    private val bellRinger: BellRinger
) : ViewModel() {


    private val _isTimerRunning: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _restTimeValue: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)
    private val _roundTimeValue: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)
    private val _whichRoundValue: MutableLiveData<Int> = MutableLiveData(DEFAULT_INTEGER_VALUE)
    private val _warningValue: MutableStateFlow<Int> = MutableStateFlow(DEFAULT_INTEGER_VALUE)
    private val _currentTime: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val _pausedTime: MutableLiveData<Long> = MutableLiveData(DEFAULT_LONG_VALUE)
    private val _roundTimeState: MutableStateFlow<Int> = MutableStateFlow(ROUND_TIME_STATE)

    private var _isResting: MutableLiveData<Boolean> = MutableLiveData()
    val isResting: LiveData<Boolean> get() = _isResting
    private var _clockTicking: MutableLiveData<String?> = MutableLiveData(null)
    val clockTicking: LiveData<String?> get() = _clockTicking
    private var _roundCounter: MutableLiveData<Int> = MutableLiveData(DEFAULT_ROUND_COUNTER_VALUE)
    val roundCounter: LiveData<Int> get() = _roundCounter

    val roundTimeState: StateFlow<Int>
        get() = _roundTimeState

    val warningValue: StateFlow<Int>
        get() = _warningValue.asStateFlow()

    val currentTime: StateFlow<Long?>
        get() = _currentTime

    val pausedTime: LiveData<Long>
        get() = _pausedTime

    val isTimerRunning: LiveData<Boolean>
        get() = _isTimerRunning

    val restTimeValue: LiveData<Int>
        get() = _restTimeValue

    val roundTimeValue: LiveData<Int>
        get() = _roundTimeValue

    val whichRoundValue: LiveData<Int>
        get() = _whichRoundValue

    init {
        observeTimer()
    }

    fun startCounting() {
        setTimmerIsRunning(true)
        when (roundTimeState.value) {
            ROUND_TIME_STATE -> {
                if (pausedTime.value?.toInt() != DEFAULT_INTEGER_VALUE) {
                    startTimer(pausedTime.value?.times(ONE_SECOND) ?: DEFAULT_LONG_VALUE) {
                        setPauseTime(DONE)
                        when {
                            (roundCounter.value
                                ?: DEFAULT_ROUND_COUNTER_VALUE) < (whichRoundValue.value
                                ?: DEFAULT_INTEGER_VALUE) -> {
                                startingTimerForRoundOnly()
                            }
                            else -> {
                                resetAll()
                                endBellSound()
                            }
                        }
                    }
                } else {
                    startBellSound()
                    startTimer(roundTimeMapper(roundTimeValue.value)) {
                        when {
                            (roundCounter.value
                                ?: DEFAULT_ROUND_COUNTER_VALUE) < (whichRoundValue.value
                                ?: DEFAULT_INTEGER_VALUE) -> {
                                startingTimerForRoundOnly()
                            }
                            else -> {
                                resetAll()
                                endBellSound()
                            }
                        }
                    }
                }
            }
            REST_TIME_STATE -> {
                if (pausedTime.value?.toInt() != DEFAULT_INTEGER_VALUE) {
                    startTimer(pausedTime.value?.times(ONE_SECOND) ?: DEFAULT_LONG_VALUE) {
                        setPauseTime(DONE)
                        startingTimerForRestOnly()
                    }
                } else {
                    startTimer(restTimeMapper(restTimeValue.value)) {
                        startingTimerForRestOnly()
                    }
                }
            }
        }
    }

    private fun observeTimer() {
        viewModelScope.launch {
            combine(roundTimeState, currentTime, warningValue) { state, timeTicking, warning ->
                GenericTriple(state, timeTicking, warning)
            }.collect {
                when (it.data1) {
                    ROUND_TIME_STATE -> {
                        it.data2?.let { timeTicking ->
                            val value = DateUtils.formatElapsedTime(timeTicking)
                            setClockTicking(value)
                            setResting(false)

                            if (it.data3 != DEFAULT_INTEGER_VALUE) {
                                if (value == "00:${it.data3}") {
                                    warningBellSound()
                                }
                            }

                        }
                    }
                    REST_TIME_STATE -> {
                        it.data2?.let { timeTicking ->
                            val value = DateUtils.formatElapsedTime(timeTicking)
                            setClockTicking(value)

                            if (value != "00:00") {
                                setResting(true)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun startTimer(
        durationTime: Long,
        finishTicking: () -> Unit
    ) {
        timerHelper.startTimer(durationTime = durationTime,
            onFinish = {
                setCurrentTime(DONE)
                setPauseTime(DONE)
                finishTicking.invoke()
            }, onTicking = { millisUntilFinished ->
                setCurrentTime((millisUntilFinished / ONE_SECOND))
                setPauseTime((millisUntilFinished / ONE_SECOND))
            })
    }

    private fun startingTimerForRoundOnly() {
        if ((restTimeValue.value ?: DEFAULT_INTEGER_VALUE) == DEFAULT_INTEGER_VALUE) {
            incrementRoundCounter()
            setIsRoundTimeRunning(ROUND_TIME_STATE)
            startCounting()
        } else {
            endBellSound()
            setIsRoundTimeRunning(REST_TIME_STATE)
            startCounting()
        }
    }

    private fun startingTimerForRestOnly() {
        incrementRoundCounter()
        setIsRoundTimeRunning(ROUND_TIME_STATE)
        startCounting()
    }

    fun setRoundTime(data: Int) {
        _roundTimeValue.value = data
    }

    private fun resetRoundTime() {
        _roundTimeValue.value = DEFAULT_INTEGER_VALUE
    }

    private fun roundTimeMapper(data: Int?): Long {
        return if (data != null) {
            when (data) {
                0 -> TimerConstant.setCustomMinutes(1)
                1 -> TimerConstant.setCustomMinutes(2)
                2 -> TimerConstant.setCustomMinutes(3)
                3 -> TimerConstant.setCustomMinutes(4)
                4 -> TimerConstant.setCustomMinutes(5)
                5 -> TimerConstant.setCustomMinutes(6)
                6 -> TimerConstant.setCustomMinutes(7)
                7 -> TimerConstant.setCustomMinutes(8)
                8 -> TimerConstant.setCustomMinutes(9)
                9 -> TimerConstant.setCustomMinutes(10)
                else -> TimerConstant.setCustomMinutes(15)
            }
        } else {
            TimerConstant.setCustomMinutes(1)
        }
    }

    private fun restTimeMapper(data: Int?): Long {
        return if (data != null) {
            when (data) {
                0 -> TimerConstant.setCustomTime(0)
                1 -> TimerConstant.setCustomTime(15)
                2 -> TimerConstant.setCustomTime(30)
                3 -> TimerConstant.setCustomTime(60)
                4 -> TimerConstant.setCustomTime(90)
                5 -> TimerConstant.setCustomTime(120)
                6 -> TimerConstant.setCustomTime(150)
                else -> TimerConstant.setCustomTime(180)
            }
        } else {
            TimerConstant.setCustomTime(0)
        }
    }

    fun resetAll() {
        resetRoundTime()
        resetRestTime()
        resetRoundCounter()
        resetClockTicking()
        resetPauseTime()
        resetResting()
        resetWhichRound()
        cancelAllTimer()
        setCurrentTime(DONE)
        setPauseTime(DONE)
        setTimmerIsRunning(false)
        setIsRoundTimeRunning(ROUND_TIME_STATE)
        resetWarningValue()
    }

    fun cancelAllTimer() {
        timerHelper.stopTimer()
    }

    private fun setCurrentTime(data: Long) {
        _currentTime.value = data
    }

    private fun setPauseTime(data: Long) {
        _pausedTime.value = data
    }

    private fun resetPauseTime() {
        _pausedTime.value = DEFAULT_LONG_VALUE
    }

    fun setRestTime(data: Int) {
        _restTimeValue.value = data
    }

    private fun resetRestTime() {
        _restTimeValue.value = DEFAULT_INTEGER_VALUE
    }

    private fun incrementRoundCounter() {
        _roundCounter.value = (_roundCounter.value)?.plus(1)
    }

    private fun resetRoundCounter() {
        _roundCounter.value = DEFAULT_ROUND_COUNTER_VALUE
    }

    private fun setClockTicking(data: String?) {
        _clockTicking.value = data
    }

    private fun resetClockTicking() {
        _clockTicking.value = null
    }


    private fun setResting(data: Boolean) {
        _isResting.value = data
    }

    private fun resetResting() {
        setResting(false)
    }

    fun setWhichRound(data: Int) {
        if (data == 16){
            //set infinity
            _whichRoundValue.value = Int.MAX_VALUE
        } else _whichRoundValue.value = data
    }

    private fun resetWhichRound() {
        _whichRoundValue.value = DEFAULT_WHICH_ROUND_COUNTER_VALUE
    }

    fun setWarningValue(data: Int) {
        _warningValue.value = data
    }

    private fun resetWarningValue() {
        _warningValue.value = DEFAULT_INTEGER_VALUE
    }

    private fun setTimmerIsRunning(data: Boolean) {
        _isTimerRunning.value = data
    }

    private fun setIsRoundTimeRunning(data: Int) {
        _roundTimeState.value = data
    }

    private fun startBellSound() {
        bellRinger.startBellSound()
    }

    private fun endBellSound() {
        bellRinger.endBellSound()
    }

    private fun warningBellSound() {
        bellRinger.warningBellSound()
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllTimer()
    }

}
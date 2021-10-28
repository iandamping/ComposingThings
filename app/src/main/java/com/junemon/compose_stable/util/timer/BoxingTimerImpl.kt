package com.junemon.compose_stable.util.timer

import android.os.CountDownTimer
import com.junemon.compose_stable.util.TimerConstant
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 29,August,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class BoxingTimerImpl @Inject constructor() : BoxingTimer {

    private var timer: CountDownTimer? = null

    override fun startTimer(durationTime: Long, onFinish: () -> Unit, onTicking: (Long) -> Unit) {
        timer = object : CountDownTimer(durationTime, TimerConstant.ONE_SECOND) {
            override fun onFinish() {
                onFinish.invoke()
            }

            override fun onTick(millisUntilFinished: Long) {
                onTicking(millisUntilFinished)
            }
        }.start()
    }

    override fun stopTimer() {
        if (timer !=null){
            timer?.cancel()
            timer = null
        }
    }


}
package com.junemon.compose_stable.util.timer

/**
 * Created by Ian Damping on 29,August,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface TimerHelper {

    fun startTimer(durationTime: Long, onFinish : () ->Unit, onTicking:(Long) ->Unit)

    fun stopTimer()
}
package com.junemon.compose_stable


/**
 * Created by Ian Damping on 28,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class TimerState {
    data class RoundTime(val time:Long) : TimerState()
    data class RestTime(val time:Long) : TimerState()
}

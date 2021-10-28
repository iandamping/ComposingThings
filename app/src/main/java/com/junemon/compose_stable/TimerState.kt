package com.junemon.compose_stable


/**
 * Created by Ian Damping on 28,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class TimerState {
    object RoundTime : TimerState()
    object RestTime : TimerState()
}

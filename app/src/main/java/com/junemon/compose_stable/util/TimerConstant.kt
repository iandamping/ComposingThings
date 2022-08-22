package com.junemon.compose_stable.util

import android.view.View
import androidx.compose.ui.graphics.Color
import com.junemon.compose_stable.RestTime
import timber.log.Timber

/**
 * Created by Ian Damping on 18,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

object TimerConstant {
    const val MY_REQUEST_CODE: Int = 0
    const val IMMERSIVE_FLAG_TIMEOUT = 500L
    const val DEFAULT_ROUND_COUNTER_VALUE = 1
    const val DEFAULT_INTEGER_VALUE = 0
    const val DEFAULT_WHICH_ROUND_COUNTER_VALUE = 1
    const val MAX_POMODORO_ROUND = 4
    const val DEFAULT_LONG_VALUE = 0L
    const val ROUND_TIME_STATE = 0
    const val REST_TIME_STATE = 1


    const val MINUTES_IN_HOUR = 60
    const val SECS_IN_MINUTES = 60
    const val MSECS_IN_SEC = 1000

    const val DONE = 0L
    const val DONE_FLOAT = 0F

    const val ONE_SECOND = 1000L
    val LIGHT_GREEN_1 = Color(0xFF37EFBA)
    val LIGHT_GREEN_2 = Color(0xFF04B97F)
    val DARK_GREEN_1 = Color(0xFF005D57)
    val DARK_GREEN_2 = Color(0xFF004940)
    val listOfRestTime = listOf<RestTime>(
            RestTime("OFF", 0),
            RestTime("10 SEC", 10),
            RestTime("30 SEC", 30),
    )

    fun setCustomMinutes(data: Int) = data * 60 * 1000L

    fun setCustomSeconds(data: Int) = data % 60 * 1000L

    fun setCustomTime(data: Int) = data * 1000L

    fun setCustomFloat(data: Long, ticking: Long):Float = when (data) {
        1 * 60 * 1000L -> 1 - (ticking.toFloat() / 60)
        2 * 60 * 1000L -> 1 - (ticking.toFloat() / 120)
        3 * 60 * 1000L -> 1 - (ticking.toFloat() / 180)
        4 * 60 * 1000L -> 1 - (ticking.toFloat() / 240)
        5 * 60 * 1000L -> 1 - (ticking.toFloat() / 300)
        6 * 60 * 1000L -> 1 - (ticking.toFloat() / 360)
        7 * 60 * 1000L -> 1 - (ticking.toFloat() / 420)
        8 * 60 * 1000L -> 1 - (ticking.toFloat() / 480)
        9 * 60 * 1000L -> 1 - (ticking.toFloat() / 540)
        10 * 60 * 1000L -> 1 - (ticking.toFloat() / 600)
        25 * 60 * 1000L -> 1 - (ticking.toFloat() / 1500)
        else -> 1 - (ticking.toFloat() / 1500)
    }

    /** Combination of all flags required to put activity into immersive mode */
    const val FLAGS_FULLSCREEN =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
}
package com.junemon.compose_stable

import android.text.format.DateUtils
import com.junemon.compose_stable.util.TimerConstant

class DateFormatter {

    fun yourHour(data:Int):String{
        val formattedHour =  TimerConstant.setCustomTime(data * (60 * 60))
        return DateUtils.formatElapsedTime(formattedHour)
    }
}
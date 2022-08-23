package com.junemon.compose_stable

import android.text.format.DateUtils
import org.junit.Assert
import org.junit.Test

class DateUtilsTest {

    private val formatter:DateFormatter = DateFormatter()

    @Test
    fun test_date_utils(){
        val date = formatter.yourHour(1 * (60 * 60))
        Assert.assertEquals("00:00:00", date)
    }
}
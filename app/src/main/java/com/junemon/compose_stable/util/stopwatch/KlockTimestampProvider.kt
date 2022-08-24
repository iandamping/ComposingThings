package com.junemon.compose_stable.util.stopwatch

import com.soywiz.klock.DateTime
import javax.inject.Inject

class KlockTimestampProvider @Inject constructor() : TimestampProvider {

    override fun getMilliseconds(): Long =
        DateTime.nowUnixLong()
}
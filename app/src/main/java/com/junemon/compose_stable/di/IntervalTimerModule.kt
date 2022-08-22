package com.junemon.compose_stable.di

import com.junemon.compose_stable.util.ringer.BellRinger
import com.junemon.compose_stable.util.ringer.BellRingerImpl
import com.junemon.compose_stable.util.timer.TimerHelper
import com.junemon.compose_stable.util.timer.TimerHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Ian Damping on 25,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface IntervalTimerModule {

    @Binds
    @Singleton
    fun bindsTimerHelper(boxingTimerImpl: TimerHelperImpl): TimerHelper

    @Binds
    @Singleton
    fun bindsBellRinger(ringerImpl: BellRingerImpl): BellRinger
}
package com.junemon.compose_stable.di

import com.junemon.compose_stable.util.timer.BoxingTimer
import com.junemon.compose_stable.util.timer.BoxingTimerImpl
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
interface BoxingTimerModule {

    @Binds
    @Singleton
    fun bindsBoxingTimer(boxingTimerImpl: BoxingTimerImpl): BoxingTimer
}
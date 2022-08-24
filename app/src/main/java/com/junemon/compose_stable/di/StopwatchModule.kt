package com.junemon.compose_stable.di

import com.junemon.compose_stable.util.stopwatch.StopwatchListOrchestrator
import com.junemon.compose_stable.util.stopwatch.StopwatchListOrchestratorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface StopwatchModule {

    @Binds
    @Singleton
    fun bindsStopwatchListOrchestrator(stopwatchListOrchestrator: StopwatchListOrchestratorImpl): StopwatchListOrchestrator


}
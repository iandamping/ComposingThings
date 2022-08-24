package com.junemon.compose_stable.di

import com.junemon.compose_stable.util.stopwatch.KlockTimestampProvider
import com.junemon.compose_stable.util.stopwatch.TimestampProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimestampModule {

    @Binds
    fun bindTimestampProvider(klockTimestampProvider: KlockTimestampProvider): TimestampProvider
}
package com.junemon.compose_stable.di

import com.junemon.compose_stable.cache.StopwatchCacheDataSource
import com.junemon.compose_stable.cache.StopwatchCacheDataSourceImpl
import com.junemon.compose_stable.repository.StopwatchRepository
import com.junemon.compose_stable.repository.StopwatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StopwatchCoreModule {

    @Binds
    fun bindsStopwatchCacheDataSource(source: StopwatchCacheDataSourceImpl): StopwatchCacheDataSource

    @Binds
    fun bindsStopwatchRepository(repository: StopwatchRepositoryImpl): StopwatchRepository
}
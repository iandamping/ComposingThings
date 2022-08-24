package com.junemon.compose_stable.di

import com.junemon.compose_stable.di.dispatcher.BackgroundDispatcherQualifier
import com.junemon.compose_stable.di.dispatcher.IoDispatcherQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @BackgroundDispatcherQualifier
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcherQualifier
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
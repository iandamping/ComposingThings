package com.junemon.compose_stable.di

import com.junemon.compose_stable.di.dispatcher.BackgroundDispatcherQualifier
import com.junemon.compose_stable.di.dispatcher.DefaultApplicationCoroutineScope
import com.junemon.compose_stable.di.dispatcher.IoApplicationCoroutineScope
import com.junemon.compose_stable.di.dispatcher.IoDispatcherQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @DefaultApplicationCoroutineScope
    fun providesDefaultApplicationScope(
        @BackgroundDispatcherQualifier defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)


    @Provides
    @IoApplicationCoroutineScope
    fun providesIoApplicationScope(
        @IoDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
}


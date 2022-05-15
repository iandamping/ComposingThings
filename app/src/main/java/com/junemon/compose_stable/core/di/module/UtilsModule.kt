package com.junemon.compose_stable.core.di.module

import com.junemon.compose_stable.util.NetworkUtils
import com.junemon.compose_stable.util.NetworkUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {

    @Binds
    fun bindsNetworkUtils(utils: NetworkUtilsImpl): NetworkUtils
}
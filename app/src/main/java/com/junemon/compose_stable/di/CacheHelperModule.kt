package com.junemon.compose_stable.di

import com.junemon.compose_stable.cache.PreferenceHelperImpl
import com.permatabank.qram.core.data.datasource.cache.preference.PreferenceHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CacheHelperModule{

    @Binds
    fun bindsPreferenceHelper(preferenceHelper: PreferenceHelperImpl): PreferenceHelper
}
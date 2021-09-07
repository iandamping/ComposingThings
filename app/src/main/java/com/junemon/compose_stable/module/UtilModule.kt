package com.junemon.compose_stable.module

import com.junemon.compose_stable.util.provider.DeviceHelper
import com.junemon.compose_stable.util.provider.DeviceHelperImpl
import com.junemon.compose_stable.util.security.FTAes
import com.junemon.compose_stable.util.security.FTAesImpl
import com.permatabank.qram.core.data.datasource.cache.preference.PreferenceHelper
import com.permatabank.qram.core.data.datasource.cache.preference.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface UtilModule {

    @Binds
    @Singleton
    fun bindsDeviceHelper(deviceHelper: DeviceHelperImpl): DeviceHelper

    @Binds
    @Singleton
    fun bindsPreferenceHelper(preferences: PreferenceHelperImpl): PreferenceHelper

    @Binds
    @Singleton
    fun bindsFTAes(ftaes: FTAesImpl): FTAes
}
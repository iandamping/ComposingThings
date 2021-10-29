package com.junemon.compose_stable

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
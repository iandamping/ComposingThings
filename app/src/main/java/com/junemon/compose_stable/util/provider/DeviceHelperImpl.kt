package com.junemon.compose_stable.util.provider

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.junemon.compose_stable.util.Constant.DEVICE_ID_PROVIDER_KEY
import com.permatabank.qram.core.data.datasource.cache.preference.PreferenceHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class DeviceHelperImpl @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    @ApplicationContext private val context: Context
) : DeviceHelper {

    @SuppressLint("MissingPermission", "HardwareIds")
    override fun getDeviceID(): String {
        val tManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return when {
            Build.VERSION.SDK_INT < 26 -> {
                Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }

            Build.VERSION.SDK_INT > 25 -> {
                provideBackupDeviceId()
            }
            else -> {
                tManager.deviceId ?: provideBackupDeviceId()
            }
        } ?: provideBackupDeviceId()
    }

    private fun provideBackupDeviceId(): String {
        return if (preferenceHelper.getStringInSharedPreference(DEVICE_ID_PROVIDER_KEY) != "") {
            preferenceHelper.getStringInSharedPreference(DEVICE_ID_PROVIDER_KEY)
        } else {
            val randomValue = UUID.randomUUID().toString().replace("-", "")
            preferenceHelper.saveStringInSharedPreference(DEVICE_ID_PROVIDER_KEY, randomValue)

            preferenceHelper.getStringInSharedPreference(DEVICE_ID_PROVIDER_KEY)
        }
    }
}

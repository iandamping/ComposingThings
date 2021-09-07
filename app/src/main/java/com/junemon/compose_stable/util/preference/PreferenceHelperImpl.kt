package com.permatabank.qram.core.data.datasource.cache.preference

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Ian Damping on 30,June,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PreferenceHelperImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PreferenceHelper {
    private var preHelperEditor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun saveStringInSharedPreference(key: String?, value: String?) {
        preHelperEditor.putString(key, value).apply()
    }

    override fun getStringInSharedPreference(key: String?): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun saveIntInSharedPreference(key: String?, value: Int?) {
        if (value != null) {
            preHelperEditor.putInt(key, value).apply()
        }
    }

    override fun getIntInSharedPreference(key: String?): Int? {
        return sharedPreferences.getInt(key, 0)
    }

    override fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        preHelperEditor.putBoolean(key, value).apply()
    }

    override fun getBooleanInSharedPreference(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun deleteSharedPreference(key: String) {
        preHelperEditor.remove(key).apply()
    }

    override fun deleteAllSharedPrefrence() {
        preHelperEditor.clear().apply()
    }
}

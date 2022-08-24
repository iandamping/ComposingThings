package com.permatabank.qram.core.data.datasource.cache.preference


/**
 * Created by Ian Damping on 30,June,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PreferenceHelper {

    fun saveStringInSharedPreference(key: String, value: String)

    fun getStringInSharedPreference(key: String): String

    fun saveIntInSharedPreference(key: String, value: Int)

    fun getIntInSharedPreference(key: String): Int

    fun saveBooleanInSharedPreference(key: String, value: Boolean)

    fun getBooleanInSharedPreference(key: String): Boolean

    fun deleteSharedPreference(key: String)

    fun deleteAllSharedPrefrence()

}

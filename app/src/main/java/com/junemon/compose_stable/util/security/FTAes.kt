package com.junemon.compose_stable.util.security


/**
 * Created by Ian Damping on 26,April,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface FTAes {

   fun encrypt(input: String, key: String): String

    fun decyrpt(input: String, aesKey: String): String

    fun sha256(base: String): String
}
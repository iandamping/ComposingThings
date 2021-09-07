package com.junemon.compose_stable.util.security

import android.annotation.SuppressLint
import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class FTAesImpl @Inject constructor(): FTAes {

    override fun encrypt(input: String, key: String): String {
        var crypted: ByteArray? = null
        try {
            val e = SecretKeySpec(key.toByteArray(), "AES")
            @SuppressLint("GetInstance") val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(1, e)
            crypted = cipher.doFinal(input.toByteArray())
        } catch (var5: Exception) {
            println(var5.toString())
        }

        return if (crypted != null) {
            String(Base64.encode(crypted, Base64.NO_WRAP))
        } else {
            ""
        }
    }

    /**this is AES_256_CBC with IVkey*/
    override fun decyrpt(input: String, aesKey: String): String {
        var output: ByteArray? = null
        try {
            val e = SecretKeySpec(aesKey.toByteArray(Charsets.UTF_8), "AES")
            @SuppressLint("GetInstance") val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")

            cipher.init(Cipher.DECRYPT_MODE, e)
            output = cipher.doFinal(Base64.decode(input, Base64.NO_WRAP))
        } catch (var5: Exception) {
            println(var5.toString())
        }

        return output?.let { String(it) } ?: ""
    }

    override fun sha256(base: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(base.toByteArray(charset("UTF-8")))
            val hexString = StringBuffer()

            hash.indices.forEachIndexed { _, i ->
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }
}

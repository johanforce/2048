package com.jarvis.calendarevent.common

import com.jarvis.calendarevent.data.remote.ZingApi
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object DataUtils {
    fun getSHA512HMac(string: String, key: String): String {
        val result = ""
        try {
            val shaHMAC: Mac = Mac.getInstance("HmacSHA512")
            val secretkey = SecretKeySpec(key.toByteArray(charset("UTF-8")), "HmacSHA512")
            shaHMAC.init(secretkey)
            return printHexBinary(shaHMAC.doFinal(string.toByteArray(charset("UTF-8"))))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return result
    }

    fun getSHA256Hash(string: String): String {
        val result = ""
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(string.toByteArray(charset("UTF-8")))
            return printHexBinary(hash)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return result
    }

    fun printHexBinary(data: ByteArray): String {
        val herChars = "0123456789abcdef".toCharArray()
        val r = StringBuilder(data.size * 2)
        data.forEach { b ->
            val i = b.toInt()
            r.append(herChars[i shr 4 and 0xF])
            r.append(herChars[i and 0xF])
        }
        return r.toString()
    }

    fun hashParamHome(path: String, ctime: Long, count: Int, version: String): String {
        return getSHA512HMac(
            path + getSHA256Hash("count=${count}ctime=${ctime}page=1version=${version}"),
            ZingApi.secretKey,
        );
    }

    fun hashParamNoId(path: String, ctime: Long, version: String): String {
        return getSHA512HMac(
            path + getSHA256Hash("ctime=${ctime}version=${version}"),
            ZingApi.secretKey,
        )
    }

    fun hashParam(path: String, id: String, ctime: Long): String {
        return getSHA512HMac(
            path + getSHA256Hash("ctime=${ctime}id=${id}version=${ZingApi.version}"),
            ZingApi.secretKey
        )
    }
}
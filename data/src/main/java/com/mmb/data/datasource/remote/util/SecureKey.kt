package com.mmb.data.datasource.remote.util

import android.util.Base64
import com.mmb.data.BuildConfig
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

internal object SecureKey {
    /**
     * A native method that is implemented by the 'secure-lib' native library,
     * which is packaged with this application.
     */
    private external fun publicKey(): String

    init {
        System.loadLibrary("secure-lib")
    }

    fun getApiKey(): String {
        return decryptApiKey()
    }

    private fun decryptApiKey(): String {
        val keyBytes = Base64.decode(publicKey(), Base64.DEFAULT)
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val pubKey = keyFactory.generatePublic(keySpec) as PublicKey

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, pubKey)
        val decryptedUrlBytes = cipher.doFinal(Base64.decode(BuildConfig.API_KEY, Base64.DEFAULT))

        return String(decryptedUrlBytes)
    }
}
package com.mmb.data.remote.interceptor

import com.mmb.data.remote.util.SecureKey
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("X-CoinAPI-Key", SecureKey.getApiKey()).build()
        return chain.proceed(authenticatedRequest)
    }
}
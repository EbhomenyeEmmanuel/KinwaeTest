package com.example.kinwaetest.data.api.interceptors

import com.example.kinwaetest.data.api.ApiParameters.AUTH_HEADER
import com.example.kinwaetest.data.api.ApiParameters.NO_AUTH_HEADER
import com.example.kinwaetest.data.api.ApiParameters.TOKEN_TYPE
import com.example.kinwaetest.data.preferences.ApiPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val apiPreferences: ApiPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = apiPreferences.getToken()
        var request = chain.request()
        if (chain.request().headers[NO_AUTH_HEADER] != null) {
            if (!token.isNullOrEmpty()) {  //For Valid tokens
                request = chain.createAuthenticatedRequest(token)
            }
        }
        return chain.proceed(request)
    }

    private fun Interceptor.Chain.createAuthenticatedRequest(token: String): Request {
        return request()
            .newBuilder()
            .addHeader(AUTH_HEADER, TOKEN_TYPE + token)
            .build()
    }

}

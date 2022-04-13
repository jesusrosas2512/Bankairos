package com.jesusrosas.kairosds.bankairos.core

import com.jesusrosas.kairosds.bankairos.Constants.HEADER_TOKEN
import com.jesusrosas.kairosds.bankairos.data.model.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                HEADER_TOKEN, TokenProvider.token.token
            )
            .build()

        return chain.proceed(request)
    }
}
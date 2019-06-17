package com.jpardogo.patinetes.data.common

import okhttp3.Interceptor
import okhttp3.Response


class AccessTokenInterceptor(private val accessTokenProvider: AccessTokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.header(HEADER_NO_AUTHORIZATION) == null) {
            request
                .newBuilder()
                .url(
                    request
                        .url()
                        .newBuilder()
                        .addQueryParameter("access_token", accessTokenProvider.getToken())
                        .build()
                )
        }

        return chain.proceed(request)
    }
}
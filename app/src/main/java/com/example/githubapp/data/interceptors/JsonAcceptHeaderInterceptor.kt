package com.example.githubapp.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class JsonAcceptHeaderInterceptor @Inject constructor() : Interceptor {

    private companion object {
        const val ACCEPT_KEY = "Accept"
        const val JSON = "application/json"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder().addHeader(ACCEPT_KEY, JSON).build()
        )
    }
}

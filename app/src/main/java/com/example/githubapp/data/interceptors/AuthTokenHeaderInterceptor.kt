package com.example.githubapp.data.interceptors

import com.example.githubapp.data.datasource.CredentialsDatasource
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthTokenHeaderInterceptor @Inject constructor(
    private val credentialsDatasource: CredentialsDatasource,
) : Interceptor {

    private companion object {
        const val AUTHORIZATION_KEY = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder().apply {
                val token = credentialsDatasource.getAccessToken()
                if (!token.isNullOrEmpty()) {
                    addHeader(AUTHORIZATION_KEY, "Bearer $token")
                }
            }.build()
        )
    }
}

package com.example.githubapp.data.credencials

import com.example.githubapp.core.sharedPrefs.SharedPrefs
import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.models.AccessTokenResponse
import com.example.githubapp.data.models.NetworkResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsDatasource @Inject constructor(
    private val credentialsApi: CredentialsApi,
    private val sharedPrefs: SharedPrefs,
) {

    private companion object {
        const val ACCESS_TOKEN_SHARED_PREFS_KEY = "access_token"
    }

    private var cachedToken: String? = null

    suspend fun authenticateUser(code: String): NetworkResource<AccessTokenResponse> {
        val call = safeApiCall {
            credentialsApi.getAccessToken(code)
        }
        if (call is NetworkResource.Success) {
            sharedPrefs.putString(ACCESS_TOKEN_SHARED_PREFS_KEY, call.value.token)
            cachedToken = call.value.token
        }
        return call
    }

    fun getAccessToken() = if (cachedToken != null) {
        cachedToken
    } else {
        cachedToken = sharedPrefs.getString(ACCESS_TOKEN_SHARED_PREFS_KEY)
        cachedToken
    }
}

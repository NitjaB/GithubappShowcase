package com.example.githubapp.data.datasource

import com.example.githubapp.data.api.ProfileApi
import com.example.githubapp.data.helpers.safeApiCall
import javax.inject.Inject

class ProfileDatasource @Inject constructor(
    private val profileApi: ProfileApi,
) {

    suspend fun getAuthUserProfile() = safeApiCall {
        profileApi.getAuthenticatedUserProfile()
    }
}

package com.example.githubapp.data.details

import com.example.githubapp.data.helpers.safeApiCall
import javax.inject.Inject

class DetailsDatasource @Inject constructor(
    private val detailsApi: DetailsApi
) {
    suspend fun getRepositoryDetails(
        owner: String,
        repoName: String
    ) = safeApiCall {
        detailsApi.getDetails(owner = owner, repo = repoName)
    }
}
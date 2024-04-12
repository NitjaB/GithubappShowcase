package com.example.githubapp.data.repositoryDetails

import com.example.githubapp.data.helpers.safeApiCall
import javax.inject.Inject

class RepositoryDetailsDatasource @Inject constructor(
    private val detailsApi: RepositoryDetailsApi
) {
    suspend fun getRepositoryDetails(
        owner: String,
        repoName: String
    ) = safeApiCall {
        detailsApi.getDetails(owner = owner, repo = repoName)
    }

    suspend fun isStarred(
        owner: String,
        repoName: String
    ) =
        safeApiCall {
            val response = detailsApi.isStarred(owner, repoName)
            when (response.code()) {
                204 -> true
                404 -> false
                else -> throw Throwable(response.code().toString())
            }
        }

    suspend fun star(
        owner: String,
        repoName: String
    ) = safeApiCall {
        val response = detailsApi.star(owner, repoName)
        when(response.code()){
            204 -> true
            else -> throw  Throwable(response.code().toString())
        }
    }
}
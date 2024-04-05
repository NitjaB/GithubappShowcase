package com.example.githubapp.data.search

import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.models.PagedData
import javax.inject.Inject

class SearchDatasource @Inject constructor(
    private val searchApi: SearchApi,
) {

    suspend fun getRepositories(query: String, page: Int) = safeApiCall {
        searchApi.getRepositories(query, page)
    }
}

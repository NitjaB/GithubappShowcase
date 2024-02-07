package com.example.githubapp.data.datasource

import com.example.githubapp.data.api.SearchApi
import com.example.githubapp.data.helpers.safeApiCall
import com.example.githubapp.data.models.PagedData
import javax.inject.Inject

class SearchDatasource @Inject constructor(
    private val searchApi: SearchApi,
) {

    suspend fun getRepositories(query: String, page: Int) = safeApiCall {
        val repoResults = searchApi.getRepositories(query, page)
        return@safeApiCall if (repoResults.items.isNotEmpty()) {
            PagedData(
                incompleteResults = true,
                items = repoResults.items
            )
        } else {
            PagedData(
                incompleteResults = false,
                items = listOf()
            )
        }
    }
}

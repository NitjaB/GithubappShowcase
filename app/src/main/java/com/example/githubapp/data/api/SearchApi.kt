package com.example.githubapp.data.api

import com.example.githubapp.data.models.PagedData
import com.example.githubapp.data.models.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PAGE_SIZE = 10
private const val FIRST_PAGE = 1

interface SearchApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = FIRST_PAGE,
        @Query("per_page") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): PagedData<RepositoryResponse>
}

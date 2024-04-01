package com.example.githubapp.data.search

import com.example.githubapp.data.models.PagedData
import com.example.githubapp.data.models.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = PagedData.FIRST_PAGE,
        @Query("per_page") pageSize: Int = PagedData.DEFAULT_PAGE_SIZE,
    ): PagedData<RepositoryResponse>
}

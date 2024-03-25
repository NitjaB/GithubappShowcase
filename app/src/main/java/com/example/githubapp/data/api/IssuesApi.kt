package com.example.githubapp.data.api

import com.example.githubapp.data.models.IssueResponse
import com.example.githubapp.data.models.PagedData
import retrofit2.http.GET
import retrofit2.http.Query

interface IssuesApi {

    @GET("issues")
    suspend fun getIssuesAndPullRequests(
        @Query("page") pageNumber: Int = PagedData.FIRST_PAGE,
        @Query("per_page") pageSize: Int = PagedData.DEFAULT_PAGE_SIZE,
    ): List<IssueResponse>
}

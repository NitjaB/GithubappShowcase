package com.example.githubapp.data.details

import com.example.githubapp.data.models.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("/repos/{owner}/{repo}")
    suspend fun getDetails(
        @Path("owner") owner:String,
        @Path("repo") repo:String
    ) : RepositoryResponse
}
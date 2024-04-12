package com.example.githubapp.feature.repositoryDetails.models


import com.example.githubapp.domain.search.models.Repository

data class DetailsScreenState(
    val details: Repository? = null,
    val isStarredByUser: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
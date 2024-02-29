package com.example.githubapp.domain.models

data class PagedData<T>(
    val isLastPage: Boolean,
    val items: List<T>,
)

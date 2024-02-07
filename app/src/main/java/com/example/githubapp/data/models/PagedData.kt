package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PagedData<T>(
    @JsonProperty("total_count") val totalCount: Int,
    @JsonProperty("incomplete_results") val incompleteResults: Boolean,
    @JsonProperty("items") val items: List<T>
)

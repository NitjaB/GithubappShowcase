package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PagedData<T>(
    @JsonProperty("items") val items: List<T>
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }
}

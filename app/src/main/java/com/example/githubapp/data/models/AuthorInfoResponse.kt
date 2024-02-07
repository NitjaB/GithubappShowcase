package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthorInfoResponse(
    @JsonProperty("login") val username: String,
    @JsonProperty("avatar_url") val avatarUrl: String,
)

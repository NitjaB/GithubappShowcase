package com.example.githubapp.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProfileResponse(
    @JsonProperty("login") val login: String,
    @JsonProperty("avatar_url") val avatarUrl: String,
    @JsonProperty("name") val name: String?,
    @JsonProperty("bio") val bio: String?,
    @JsonProperty("html_url") val htmlUrl: String,
)

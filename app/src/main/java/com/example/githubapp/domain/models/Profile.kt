package com.example.githubapp.domain.models

data class Profile(
    val login: String,
    val avatarUrl: Image,
    val name: String?,
    val bio: String?,
    val url: String,
)

package com.example.githubapp.domain.models

sealed class Image {
    data class RemoteImage(
        val url: String,
    ) : Image()

    data class LocalImage(
        val id: Int,
    ) : Image()
}

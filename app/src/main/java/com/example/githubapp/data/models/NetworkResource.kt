package com.example.githubapp.data.models

sealed class NetworkResource<out T> {
    data class Success<out T>(val value: T) : NetworkResource<T>()
    data class HttpError(
        val code: Int? = null,
        val message: String
    ) : NetworkResource<Nothing>()

    data class GenericError(
        val message: String?,
        val cause: Throwable,
    ) : NetworkResource<Nothing>()

    data object NetworkError : NetworkResource<Nothing>()
}

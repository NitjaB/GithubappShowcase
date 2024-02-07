package com.example.githubapp.data.helpers

import com.example.githubapp.data.models.NetworkResource
import com.example.githubapp.data.models.NetworkResource.*
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResource<T> =
    try {
        Success(apiCall())
    } catch (throwable: Throwable) {
        Timber.d(throwable)
        when (throwable) {
            is IOException -> NetworkError
            is HttpException -> HttpError(throwable.code(), throwable.message())
            else -> GenericError(
                message = throwable.message,
                cause = throwable,
            )
        }
    }

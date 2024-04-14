package com.example.githubapp.feature.repositoryDetails.models

sealed interface RepositoryDetailsScreenEvent {

    data object NavigateBack : RepositoryDetailsScreenEvent

    data object OnShareClicked : RepositoryDetailsScreenEvent

    data class OnStarButtonClicked(
        val owner: String,
        val repositoryName: String,
    ) : RepositoryDetailsScreenEvent
}
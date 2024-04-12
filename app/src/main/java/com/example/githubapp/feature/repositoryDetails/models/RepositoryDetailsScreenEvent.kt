package com.example.githubapp.feature.repositoryDetails.models

sealed interface RepositoryDetailsScreenEvent {

    data object NavigateBack : RepositoryDetailsScreenEvent

    data object OnShareClicked : RepositoryDetailsScreenEvent

}
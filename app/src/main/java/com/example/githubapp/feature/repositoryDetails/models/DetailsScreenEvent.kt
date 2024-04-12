package com.example.githubapp.feature.repositoryDetails.models

sealed interface DetailsScreenEvent {

    data object NavigateBack : DetailsScreenEvent

    data object OnShareClicked : DetailsScreenEvent

}
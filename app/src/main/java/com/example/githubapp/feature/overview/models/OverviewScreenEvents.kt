package com.example.githubapp.feature.overview.models

sealed interface OverviewScreenEvents {

    data object OnIssuesClicked : OverviewScreenEvents

    data object OnPullRequestClicked : OverviewScreenEvents
}

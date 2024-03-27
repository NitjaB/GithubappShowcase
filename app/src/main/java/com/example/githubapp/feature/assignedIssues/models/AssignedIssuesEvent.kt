package com.example.githubapp.feature.assignedIssues.models

sealed interface AssignedIssuesEvent {

    data object OnBackClicked : AssignedIssuesEvent

    data class OnIssueClicked(val id: Int) : AssignedIssuesEvent
}

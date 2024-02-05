package com.example.githubapp.feature.search.model

sealed interface SearchScreenEvent {

    data class onSearchTextChanged(val text: String): SearchScreenEvent

    data object onOpenFiltersClicked: SearchScreenEvent
}
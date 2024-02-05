package com.example.githubapp.feature.search.navigation

import com.example.githubapp.core.navigation.NavigationDestination

object SearchScreenRouter : NavigationDestination {

    private const val SEARCH_SCREEN_ROUTE = "search"

    override fun route() = SEARCH_SCREEN_ROUTE
}

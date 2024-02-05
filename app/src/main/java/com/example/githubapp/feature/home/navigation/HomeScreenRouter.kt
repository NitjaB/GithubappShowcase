package com.example.githubapp.feature.home.navigation

import com.example.githubapp.core.navigation.NavigationDestination

object HomeScreenRouter : NavigationDestination {

    private const val HOME_SCREEN_ROUTE = "home"

    override fun route() = HOME_SCREEN_ROUTE
}

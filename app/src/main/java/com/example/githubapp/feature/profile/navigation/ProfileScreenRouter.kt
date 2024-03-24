package com.example.githubapp.feature.profile.navigation

import com.example.githubapp.core.navigation.NavigationDestination

object ProfileScreenRouter : NavigationDestination {

    private const val PROFILE_SCREEN_ROUTE = "profile"

    override fun route() = PROFILE_SCREEN_ROUTE
}

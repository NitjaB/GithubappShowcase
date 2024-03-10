package com.example.githubapp.feature.login.navigation

import com.example.githubapp.core.navigation.NavigationDestination

object LoginScreenRouter : NavigationDestination {

    private const val LOGIN_SCREEN_ROUTE = "login"

    override fun route() = LOGIN_SCREEN_ROUTE
}

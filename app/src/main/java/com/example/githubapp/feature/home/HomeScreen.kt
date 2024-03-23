package com.example.githubapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubapp.core.components.GithubappNavigationMenu
import com.example.githubapp.core.navigation.BottomNavigationItem
import com.example.githubapp.core.components.GithubappBottomNavigationItem
import com.example.githubapp.feature.overview.OverviewScreen
import com.example.githubapp.feature.search.SearchScreen
import com.example.githubapp.feature.search.navigation.SearchScreenRouter

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            GithubappNavigationMenu {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomNavigationItem.HOME_NAVIGATION_ITEMS.forEach { screen ->
                    GithubappBottomNavigationItem(
                        screen,
                        currentDestination,
                        navController,
                    )
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
            ) {
                composable("home") { OverviewScreen(innerPadding) }
                composable(SearchScreenRouter.route()) { SearchScreen(innerPadding) }
                composable("profile") {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Text(
                            text = "Profile!"
                        )
                    }
                }
            }
        },
    )
}
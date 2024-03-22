package com.example.githubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.githubapp.core.components.GithubappNavigation
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.designSystem.theme.GithubAppTheme
import com.example.githubapp.domain.credencials.usecase.IsUserLoggedIn
import com.example.githubapp.feature.home.navigation.HomeScreenRouter
import com.example.githubapp.feature.login.navigation.LoginScreenRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var isUserLoggedIn: IsUserLoggedIn

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GithubAppTheme {
                GithubappNavigation(
                    navigator = navigator,
                    startScreen = NavigationEvent.Destination(
                        if (isUserLoggedIn()) HomeScreenRouter.route() else LoginScreenRouter.route()
                    )
                )
            }
        }
    }
}

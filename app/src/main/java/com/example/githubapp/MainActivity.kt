package com.example.githubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubapp.core.components.GithubappNavigation
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.designSystem.theme.GithubAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubAppTheme {
                GithubappNavigation(navigator)
            }
        }
    }
}

@Composable
fun HelloScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Hello!", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun HelloScreenPreview() {
    HelloScreen()
}
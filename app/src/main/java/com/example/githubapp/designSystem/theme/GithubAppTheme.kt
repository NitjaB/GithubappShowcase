package com.example.githubapp.designSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = blue,
    surface = gray700,
    background = black500,
    error = red,
    onBackground = white,
    onSurface = white,
)

private val LightColorScheme = lightColorScheme(
    primary = blue,
    surface = white,
    background = white,
    error = red,
    onBackground = black,
    onSurface = black,
)

@Composable
fun GithubAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography,
    )
}

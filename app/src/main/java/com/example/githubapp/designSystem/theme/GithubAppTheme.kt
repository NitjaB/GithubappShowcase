package com.example.githubapp.designSystem.theme

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = blue,
    surface = gray700,
    background = black,
    error = red,
    onBackground = white,
    onSurface = white,
)

private val LightColorScheme = lightColorScheme(
    primary = blue,
    surface = white,
    background = gray300,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            window.setBackgroundDrawable(ColorDrawable(colorScheme.background.toArgb()))
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography,
    )
}

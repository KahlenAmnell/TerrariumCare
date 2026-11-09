package com.example.terrariumcare.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkOliveGreen,
    onPrimary = Color.Black,
    background = DarkScreenBackground,
    surface = DarkCardBackground,
    surfaceVariant = DarkSurface,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,
    secondaryContainer = DarkSecondaryContainer,
    outline = DarkBorderColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightOliveGreen,
    onPrimary = Color.White,
    background = LightScreenBackground,
    surface = LightCardBackground,
    surfaceVariant = LightSurface,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary,
    onSurfaceVariant = LightTextSecondary,
    secondaryContainer = LightSecondaryContainer,
    outline = LightBorderColor
)

@Composable
fun TerrariumCareTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
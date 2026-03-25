package dev.game2048.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

enum class AppTheme {
    LIGHT,
    DARK,
    WATER
}

private val LightColorScheme = lightColorScheme(
    primary = GameTitle,
    onPrimary = Color.White,
    secondary = HeaderButtons,
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = TextDark,
    surface = LightSurface,
    onSurface = TextDark
)

private val DarkColorScheme = darkColorScheme(
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = DarkBackground,
    onBackground = DarkText,
    surface = DarkSurface,
    onSurface = DarkText
)

private val WaterColorScheme = darkColorScheme(
    primary = WaterPrimary,
    onPrimary = Color.Black,
    secondary = WaterSecondary,
    onSecondary = Color.Black,
    background = WaterBackground,
    onBackground = WaterText,
    surface = WaterSurface,
    onSurface = WaterText,
    error = WaterError
)

@Composable
fun Game2048Theme(
    themeType: AppTheme = if (isSystemInDarkTheme()) AppTheme.DARK else AppTheme.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        AppTheme.LIGHT -> LightColorScheme
        AppTheme.DARK -> DarkColorScheme
        AppTheme.WATER -> WaterColorScheme
    }

    val gameColors = when (themeType) {
        AppTheme.LIGHT -> LightGameColors
        AppTheme.DARK -> DarkGameColors
        AppTheme.WATER -> WaterGameColors
    }

    CompositionLocalProvider(LocalGameColors provides gameColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

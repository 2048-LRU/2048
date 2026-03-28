package dev.game2048.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

enum class Theme {
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

fun getThemeData(theme: Theme, primaryColor: Color) = when (theme) {
    Theme.LIGHT -> Triple("Light", Icons.Default.LightMode, Color(0xFFE5A000))
    Theme.DARK -> Triple("Dark", Icons.Default.DarkMode, Color(0xFF6A5ACD))
    Theme.WATER -> Triple("Water", Icons.Default.WaterDrop, primaryColor)
}

@Composable
fun Game2048Theme(
    themeType: Theme = if (isSystemInDarkTheme()) Theme.DARK else Theme.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        Theme.LIGHT -> LightColorScheme
        Theme.DARK -> DarkColorScheme
        Theme.WATER -> WaterColorScheme
    }

    val gameColors = when (themeType) {
        Theme.LIGHT -> LightGameColors
        Theme.DARK -> DarkGameColors
        Theme.WATER -> WaterGameColors
    }

    CompositionLocalProvider(LocalGameColors provides gameColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

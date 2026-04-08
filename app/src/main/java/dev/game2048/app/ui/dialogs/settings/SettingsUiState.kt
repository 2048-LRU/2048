package dev.game2048.app.ui.dialogs.settings

import dev.game2048.app.domain.model.GameSettings
import dev.game2048.app.ui.theme.Theme
import dev.game2048.app.utils.GameConstants
import kotlin.Boolean

data class SettingsUiState(
    val gridSize: Int = GameConstants.GRID_SIZE,
    val isSoundEnabled: Boolean = true,
    val isAnimationEnabled: Boolean = true,
    val isAccelerometerEnabled: Boolean = false,
    val currentTheme: Theme = Theme.SYSTEM
)

fun GameSettings.toUiState(): SettingsUiState = SettingsUiState(
    isSoundEnabled = isSoundEnabled,
    isAnimationEnabled = isAnimationEnabled,
    isAccelerometerEnabled = isAccelerometerEnabled,
    currentTheme = currentTheme,
    gridSize = gridSize
)

fun SettingsUiState.toEntity(): GameSettings = GameSettings(
    isSoundEnabled = isSoundEnabled,
    isAnimationEnabled = isAnimationEnabled,
    isAccelerometerEnabled = isAccelerometerEnabled,
    currentTheme = currentTheme,
    gridSize = gridSize
)

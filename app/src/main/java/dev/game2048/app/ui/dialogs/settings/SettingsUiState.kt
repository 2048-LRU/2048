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
    val currentTheme: Theme = Theme.SYSTEM,
    val usesImage: Boolean = false
)

fun GameSettings.toUiState(): SettingsUiState = SettingsUiState(
    isSoundEnabled = isSoundEnabled,
    isAnimationEnabled = isAnimationEnabled,
    isAccelerometerEnabled = isAccelerometerEnabled,
    currentTheme = currentTheme,
    gridSize = gridSize,
    usesImage = usesImage
)

fun SettingsUiState.toEntity(): GameSettings = GameSettings(
    isSoundEnabled = isSoundEnabled,
    isAnimationEnabled = isAnimationEnabled,
    isAccelerometerEnabled = isAccelerometerEnabled,
    currentTheme = currentTheme,
    gridSize = gridSize,
    usesImage = usesImage
)

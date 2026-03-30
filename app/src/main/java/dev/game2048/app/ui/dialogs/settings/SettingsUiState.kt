package dev.game2048.app.ui.dialogs.settings

import dev.game2048.app.domain.model.GameSettings
import dev.game2048.app.utils.GameConstants

data class SettingsUiState(
    val isLoading: Boolean = true,
    val gridSize: Int = GameConstants.GRID_SIZE,
    val settings: GameSettings = GameSettings()
)

package dev.game2048.app.ui.dialogs.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.game2048.app.data.repository.SettingsRepository
import dev.game2048.app.domain.model.GameSettings
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = combine(
        repository.gridSizeFlow,
        repository.settingsFlow
    ) { gridSize, settings ->
        SettingsUiState(isLoading = false, gridSize = gridSize, settings = settings)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SettingsUiState(isLoading = true))

    fun applySettings(size: Int, settings: GameSettings, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.saveGridSize(size)
            repository.saveSettings(settings)
            onComplete()
        }
    }
}

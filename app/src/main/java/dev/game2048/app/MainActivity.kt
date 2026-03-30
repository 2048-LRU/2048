package dev.game2048.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.game2048.app.data.local.datastore.SettingsDataStore
import dev.game2048.app.data.repository.SettingsRepository
import dev.game2048.app.domain.model.GameSettings
import dev.game2048.app.ui.navigation.AppNavHost
import dev.game2048.app.ui.screens.game.GameScreen
import dev.game2048.app.ui.theme.Game2048Theme
import dev.game2048.app.ui.theme.Theme
import dev.game2048.app.utils.MediaPlayer
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mediaPlayer = MediaPlayer()
    private lateinit var settingsRepository: SettingsRepository
    lateinit var initialSettings: GameSettings

    companion object {
        lateinit var prefs: GameSettings
    }

    init {
        lifecycleScope.launch {
            prefs = settingsRepository.getSettings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mediaPlayer.initMediaPlayer(this)

        setContent {
            var settings by remember { mutableStateOf(initialSettings) }

            Game2048Theme(themeType = settings.currentTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (prefs.isSoundEnabled) {
            mediaPlayer.startMediaPlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pauseMediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.destroyMediaPlayer()
    }
}

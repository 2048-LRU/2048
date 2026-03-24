package dev.game2048.app

import android.media.MediaPlayer
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
import dagger.hilt.android.AndroidEntryPoint
import dev.game2048.app.ui.screens.game.GameScreen
import dev.game2048.app.ui.theme.Game2048Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private val prefs by lazy { getSharedPreferences("game_prefs", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val defaultSoundState = prefs.getBoolean("sound_enabled", true)

        if (defaultSoundState) {
            initMediaPlayer()
        }

        setContent {
            var isSoundEnabled by remember { mutableStateOf(defaultSoundState) }

            Game2048Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        modifier = Modifier.padding(innerPadding),
                        isSoundEnabled = isSoundEnabled,
                        onSoundToggled = { enabled ->
                            isSoundEnabled = enabled
                            prefs.edit().putBoolean("sound_enabled", enabled).apply()

                            if (enabled) {
                                initMediaPlayer()
                                mediaPlayer?.start()
                            } else {
                                mediaPlayer?.pause()
                            }
                        }
                    )
                }
            }
        }
    }

    private fun initMediaPlayer() {
        if (mediaPlayer == null) {
            try {
                mediaPlayer = MediaPlayer.create(this, R.raw.bg_music)
                mediaPlayer?.isLooping = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (prefs.getBoolean("sound_enabled", true)) {
            mediaPlayer?.start()
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

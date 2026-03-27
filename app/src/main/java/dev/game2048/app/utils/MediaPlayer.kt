package dev.game2048.app.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import dev.game2048.app.R
import java.io.IOException

class MediaPlayer {
    private var player: MediaPlayer? = null

    fun initMediaPlayer(context: Context) {
        if (player == null) {
            try {
                player = MediaPlayer.create(context, R.raw.bg_music)
                player?.isLooping = true
            } catch (e: IOException) {
                Log.d("mediaPlayer", "couldn't create the mediaPlayer : $e")
            }
        }
    }

    fun startMediaPlayer() {
        player?.start()
    }

    fun pauseMediaPlayer() {
        player?.pause()
    }

    fun destroyMediaPlayer() {
        player?.release()
        player = null
    }
}

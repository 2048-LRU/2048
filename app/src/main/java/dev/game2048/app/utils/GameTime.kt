package dev.game2048.app.utils

import java.lang.String.format
import java.util.Locale

fun formatGameTime(totalSeconds: Long): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return format(Locale.US, "%02d:%02d", minutes, seconds)
}

package dev.game2048.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val GameTitle = Color(0xFF5C534B)
val ScoreText = Color(0xFF222222)
val HeaderButtons = Color(0xFFC73838)
val TextDark = Color(0xFF776E65)
val TextLight = Color(0xFFF9F6F2)

val Tile2048 = Color(0xFFEDC22E)

// Light theme
val LightBackground = Color(0xFFFAF8EF)
val LightSurface = Color(0xFFFFFFFF)

// Dark theme
val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF1E1E1E)
val DarkText = Color(0xFFE0E0E0)

// Water theme
val WaterBackground = Color(0xFF1E293B)
val WaterSurface = Color(0xFF334155)
val WaterText = Color(0xFFF1F5F9)
val WaterPrimary = Color(0xFF7DD3FC)
val WaterSecondary = Color(0xFF94A3B8)
val WaterError = Color(0xFFEF4444)

data class GameColors(
    val gridBackground: Color,
    val tileEmpty: Color,
    val tile2: Color,
    val tile4: Color,
    val tile8: Color,
    val tile16: Color,
    val tile32: Color,
    val tile64: Color,
    val tile128: Color,
    val tile256: Color,
    val tile512: Color,
    val tile1024: Color,
    val tile2048: Color,
    val tileSuper: Color,
    val textLow: Color,
    val textHigh: Color
) {
    fun tileColor(value: Int): Color = when (value) {
        0 -> tileEmpty
        2 -> tile2
        4 -> tile4
        8 -> tile8
        16 -> tile16
        32 -> tile32
        64 -> tile64
        128 -> tile128
        256 -> tile256
        512 -> tile512
        1024 -> tile1024
        2048 -> tile2048
        else -> tileSuper
    }

    fun tileTextColor(value: Int): Color = if (value <= 4) textLow else textHigh
}

val LightGameColors = GameColors(
    gridBackground = Color(0xFFAD9D8F), tileEmpty = Color(0x99C1B3A4),
    tile2 = Color(0xFFEEE4DA), tile4 = Color(0xFFEDE0C8), tile8 = Color(0xFFF2B179),
    tile16 = Color(0xFFF59563), tile32 = Color(0xFFF67C5F), tile64 = Color(0xFFF65E3B),
    tile128 = Color(0xFFEDCF72), tile256 = Color(0xFFEDCC61), tile512 = Color(0xFFEDC850),
    tile1024 = Color(0xFFEDC53F), tile2048 = Color(0xFFEDC22E), tileSuper = Color(0xFF3C3A32),
    textLow = Color(0xFF776E65), textHigh = Color(0xFFF9F6F2)
)

val DarkGameColors = GameColors(
    gridBackground = Color(0xFF3E3E3E), tileEmpty = Color(0x33FFFFFF),
    tile2 = Color(0xFF4A4A4A), tile4 = Color(0xFF5A5A5A), tile8 = Color(0xFFF2A154),
    tile16 = Color(0xFFE76F51), tile32 = Color(0xFFE24A33), tile64 = Color(0xFFD8281C),
    tile128 = Color(0xFFD4AF37), tile256 = Color(0xFFC5A017), tile512 = Color(0xFFB38D0D),
    tile1024 = Color(0xFF9E7C05), tile2048 = Color(0xFF8B6C00), tileSuper = Color(0xFF500000),
    textLow = Color(0xFFE0E0E0), textHigh = Color(0xFFFFFFFF)
)

val WaterGameColors = GameColors(
    gridBackground = Color(0xFF0F172A), tileEmpty = Color(0x33000000),
    tile2 = Color(0xFFBAE6FD), tile4 = Color(0xFF7DD3FC), tile8 = Color(0xFF38BDF8),
    tile16 = Color(0xFF0EA5E9), tile32 = Color(0xFF0284C7), tile64 = Color(0xFF0369A1),
    tile128 = Color(0xFF34D399), tile256 = Color(0xFF10B981), tile512 = Color(0xFF059669),
    tile1024 = Color(0xFF047857), tile2048 = Color(0xFF065F46), tileSuper = Color(0xFF1E293B),
    textLow = Color(0xFF0F172A), textHigh = Color(0xFFF8FAFC)
)

val LocalGameColors = staticCompositionLocalOf<GameColors> {
    error("GameColors not provided")
}

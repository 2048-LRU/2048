package dev.game2048.app.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.game2048.app.ui.theme.LocalGameColors
import kotlinx.coroutines.delay

@Composable
fun TutorialOverlay(moves: Int, onDismiss: () -> Unit) {
    val gameColors = LocalGameColors.current
    val initialMoves = remember { moves }
    val tutorialStep = moves - initialMoves

    LaunchedEffect(tutorialStep) {
        if (tutorialStep >= 2) {
            delay(1500)
            onDismiss()
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "swipe")

    val offsetAnimation by infiniteTransition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "swipeAnimation"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "alphaAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = if (tutorialStep >= 2) 0.8f else 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        when (tutorialStep) {
            0 -> TutorialInstruction(
                icon = Icons.Default.SwapHoriz,
                text = "Swipe to merge the tiles with the same value",
                alpha = alpha,
                offsetX = offsetAnimation.dp
            )

            1 -> TutorialInstruction(
                icon = Icons.Default.SwapVert,
                text = "Now try swiping vertically",
                alpha = alpha,
                offsetY = offsetAnimation.dp
            )

            else -> {
                Text(
                    text = "Great!\nNow let's go for 2048.",
                    color = gameColors.settings,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp
                )
            }
        }
    }
}

@Composable
private fun TutorialInstruction(icon: ImageVector, text: String, alpha: Float, offsetX: Dp = 0.dp, offsetY: Dp = 0.dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.offset(y = 30.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Swipe indicator",
            tint = Color.White.copy(alpha = alpha),
            modifier = Modifier
                .size(80.dp)
                .offset(x = offsetX, y = offsetY)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

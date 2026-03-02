package dev.game2048.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.game2048.app.ui.components.GameGrid
import dev.game2048.app.ui.theme.GameTitle
import dev.game2048.app.ui.theme.TextLight
import dev.game2048.app.ui.theme.Tile2048

@Suppress("FunctionNaming")
@Composable
fun GameScreen(modifier: Modifier = Modifier, viewModel: GameViewModel = viewModel()) {
    val board by viewModel.board.collectAsState()
    val isGameOver by viewModel.isGameOver.collectAsState()
    val hasWon by viewModel.hasWon.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "2048",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = GameTitle
        )

        GameGrid(
            board = board,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            onMove = { direction -> viewModel.onMove(direction) }
        )

        if (isGameOver || hasWon) {
            val text = if (hasWon) "YOU WIN!" else "GAME OVER"

            val backgroundColor = if (hasWon) {
                Tile2048.copy(alpha = 0.5f)
            } else {
                Color.Black.copy(alpha = 0.4f)
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = backgroundColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = text,
                        color = TextLight,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

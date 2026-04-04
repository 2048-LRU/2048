package dev.game2048.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.game2048.app.domain.model.GameState
import dev.game2048.app.ui.screens.game.GameUiState
import dev.game2048.app.ui.theme.Game2048Theme

@Composable
fun GameOverlay(
    uistate: GameUiState,
    showRecap: Boolean,
    onShareGrid: () -> Unit,
    onRequestRecap: () -> Unit,
    onRestart: () -> Unit,
    onContinue: () -> Unit
) {
    val isWin = uistate.state == GameState.Won
    val backgroundAlpha = if (isWin || !showRecap) 0.4f else 0.95f
    val backgroundColor = if (isWin) {
        MaterialTheme.colorScheme.primary.copy(alpha = backgroundAlpha)
    } else {
        MaterialTheme.colorScheme.background.copy(alpha = backgroundAlpha)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 80.dp)
    ) {
        if (isWin || !showRecap) {
            OverlayTitle(
                isWin = isWin,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        if (!isWin && showRecap) {
            GameRecap(
                score = uistate.score,
                bestScore = uistate.bestScore,
                moves = uistate.moves,
                topTile = uistate.currentTopTile,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val primaryColor = MaterialTheme.colorScheme.primary
            val secondaryColor = MaterialTheme.colorScheme.secondary
            val winValue = uistate.winTarget * 2

            when {
                isWin -> {
                    OverlayButton(
                        "Continue for $winValue",
                        primaryColor,
                        MaterialTheme.colorScheme.onPrimary,
                        onContinue
                    )
                }

                !showRecap -> {
                    OverlayButton("Share Board", secondaryColor, MaterialTheme.colorScheme.onSecondary, onShareGrid)
                    OverlayButton("New Game", primaryColor, MaterialTheme.colorScheme.onPrimary, onRequestRecap)
                }

                else -> {
                    OverlayButton("Start Playing", primaryColor, MaterialTheme.colorScheme.onPrimary, onRestart)
                }
            }
        }
    }
}

@Composable
private fun OverlayTitle(isWin: Boolean, modifier: Modifier = Modifier) {
    Text(
        text = if (isWin) "YOU WIN!" else "GAME OVER",
        color = if (isWin) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
        fontSize = 48.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Composable
private fun OverlayButton(text: String, color: Color, textColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun GameRecap(score: Int, bestScore: Int, moves: Int, topTile: Int, modifier: Modifier = Modifier) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val textColor = MaterialTheme.colorScheme.onSurface

    Column(
        modifier = modifier
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(12.dp))
            .background(surfaceColor)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Game Results",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        HorizontalDivider(color = textColor.copy(alpha = 0.15f))

        RecapRow(label = "All time best score", value = formatStat(bestScore), textColor = textColor)
        RecapRow(label = "Score", value = formatStat(score), textColor = textColor)
        RecapRow(label = "Best Tile", value = formatStat(topTile), textColor = textColor)
        RecapRow(label = "Moves", value = formatStat(moves), textColor = textColor)
    }
}

@Composable
private fun RecapRow(label: String, value: String, textColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 16.sp, color = textColor)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor)
    }
}

private fun formatStat(value: Int): String = "%,d".format(value).replace(',', ' ')

@Preview(showBackground = true, name = "Win Phase")
@Composable
private fun GameOverlayWinPreview() {
    Game2048Theme {
        GameOverlay(
            uistate = GameUiState(state = GameState.Won, score = 20400080, bestScore = 20480, moves = 834),
            showRecap = false,
            onShareGrid = {},
            onRequestRecap = {},
            onRestart = {},
            onContinue = {}
        )
    }
}

@Preview(showBackground = true, name = "Game Over, Share Board Phase")
@Composable
private fun GameOverlayGameOverSharePreview() {
    Game2048Theme {
        GameOverlay(
            uistate = GameUiState(state = GameState.Over, score = 3456, bestScore = 15204, moves = 142),
            showRecap = false,
            onShareGrid = {},
            onRequestRecap = {},
            onRestart = {},
            onContinue = {}
        )
    }
}

@Preview(showBackground = true, name = "Game Over, Stats Phase")
@Composable
private fun GameOverlayGameOverStatsPreview() {
    Game2048Theme {
        GameOverlay(
            uistate = GameUiState(state = GameState.Over, score = 3456, bestScore = 1520004, moves = 142),
            showRecap = true,
            onShareGrid = {},
            onRequestRecap = {},
            onRestart = {},
            onContinue = {}
        )
    }
}

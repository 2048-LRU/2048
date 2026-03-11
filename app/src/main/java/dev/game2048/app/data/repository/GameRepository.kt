package dev.game2048.app.data.repository

import dev.game2048.app.data.local.dao.GameDao
import dev.game2048.app.data.local.entity.GameEntity
import dev.game2048.app.domain.model.GameState
import dev.game2048.app.domain.model.HistoryState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(private val gameDao: GameDao) {

    suspend fun saveGame(
        board: List<List<Int>>,
        score: Int,
        winTarget: Int,
        state: GameState,
        history: List<HistoryState>
    ) {
        val entity = GameEntity(
            board = Json.encodeToString(board),
            score = score,
            winTarget = winTarget,
            state = when (state) {
                GameState.Playing -> "Playing"
                GameState.Won -> "Won"
                GameState.Over -> "Over"
            },
            history = Json.encodeToString(history)
        )
        gameDao.saveGameState(entity)
    }

    suspend fun loadGame(): SavedGame? {
        val entity = gameDao.getGameState() ?: return null
        return SavedGame(
            board = Json.decodeFromString(entity.board),
            score = entity.score,
            winTarget = entity.winTarget,
            state = when (entity.state) {
                "Won" -> GameState.Won
                "Over" -> GameState.Over
                else -> GameState.Playing
            },
            history = Json.decodeFromString(entity.history)
        )
    }

    suspend fun deleteGame() {
        gameDao.deleteGameState()
    }
}

data class SavedGame(
    val board: List<List<Int>>,
    val score: Int,
    val winTarget: Int,
    val state: GameState,
    val history: List<HistoryState>
)


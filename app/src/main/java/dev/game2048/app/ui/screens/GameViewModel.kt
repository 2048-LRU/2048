package dev.game2048.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.game2048.app.data.Direction
import dev.game2048.app.data.GameEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val engine = GameEngine(GRID_SIZE)
    private var isMoving = false

    private val _board = MutableStateFlow(emptyBoard())
    val board: StateFlow<List<List<Int>>> = _board.asStateFlow()

    init {
        engine.startGame()
        _board.value = engine.board()
    }

    fun onMove(direction: Direction) {
        if (isMoving) {
            return
        }

        viewModelScope.launch {
            isMoving = true

            if (engine.move(direction)) {
                _board.value = engine.board()
                delay(80)
                engine.spawnRandomTile()
                _board.value = engine.board()
            }
            isMoving = false
        }
    }

    private companion object {
        const val GRID_SIZE = 4

        fun emptyBoard(): List<List<Int>> = List(GRID_SIZE) { List(GRID_SIZE) { 0 } }
    }
}

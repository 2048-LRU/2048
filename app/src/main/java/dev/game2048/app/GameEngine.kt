package dev.game2048.app

class GameEngine(private val size: Int = 4) {
    val board: Array<Array<Int>> = Array(size) { Array(size) { 0 } }

    fun startGame() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                board[row][col] = 0
            }
        }

        spawnRandomTile()
        spawnRandomTile()
    }

    fun spawnRandomTile() {
        val emptyCells = mutableListOf<Pair<Int, Int>>()

        for (row in 0 until size) {
            for (col in 0 until size) {
                if (board[row][col] == 0) {
                    emptyCells.add(Pair(row, col))
                }
            }
        }

        // In 2048 you have a 10% chance to get a 4 instead of a 2
        if (emptyCells.isNotEmpty()) {
            val (randomRow, randomCol) = emptyCells.random()
            board[randomRow][randomCol] = if (Math.random() < 0.9) 2 else 4
        }
    }
}

package dev.game2048.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.game2048.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var gameEngine: GameEngine

    // used for the tiles displaying
    private lateinit var tiles: Array<Array<TileView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gameEngine = GameEngine(4)
        createGrid(4)
        gameEngine.startGame()
        updateUI()
    }

    private fun createGrid(dims: Int) {
        tiles =
            Array(dims) { row ->
                Array(dims) { col ->
                    val tile = createTile(row, col)
                    binding.grid.addView(tile)
                    tile
                }
            }
    }

    private fun createTile(row: Int, col: Int): TileView {
        val tile = TileView(this)
        tile.setGridPosition(row, col)
        return tile
    }

    private fun updateUI() {
        val currentBoard = gameEngine.board

        for (row in currentBoard.indices) {
            for (col in currentBoard[row].indices) {
                val value = currentBoard[row][col]
                tiles[row][col].setValue(value)
            }
        }
    }
}

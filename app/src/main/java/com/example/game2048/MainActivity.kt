package com.example.game2048

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    // used later to do the calculations
    private lateinit var board : Array<Array<Int>>
    // used for the tiles displaying
    private lateinit var tiles: Array<Array<TextView>>
    private lateinit var grid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        grid = findViewById(R.id.grid)
        createGrid(4)
    }

    private fun createGrid(dims : Int) {
        tiles = Array(dims) { row ->
            Array(dims) { col ->
                val tile = createTile(row, col)
                grid.addView(tile)
                tile
            }
        }
    }

    private fun createTile(row : Int, col : Int): TextView {
        val tile = TextView(this)

        val params = GridLayout.LayoutParams().apply {
            width = 0
            height = 0
            rowSpec = GridLayout.spec(row, 1f)
            columnSpec = GridLayout.spec(col, 1f)
            setMargins(8, 8, 8, 8)
        }

        tile.layoutParams = params
        tile.gravity = Gravity.CENTER
        tile.textSize = 24f
        tile.setTypeface(null, Typeface.BOLD)
        tile.setBackgroundResource(R.drawable.tile_background)

        return tile
    }
}
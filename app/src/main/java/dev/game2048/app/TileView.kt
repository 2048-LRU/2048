package dev.game2048.app

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.GridLayout

class TileView(context: Context, attrs: AttributeSet? = null) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    init {
        gravity = Gravity.CENTER
        textSize = 24f
        setTypeface(null, Typeface.BOLD)
        setBackgroundResource(R.drawable.tile_background)
    }

    fun setGridPosition(row: Int, col: Int) {
        layoutParams = GridLayout.LayoutParams().apply {
            width = 0
            height = 0
            rowSpec = GridLayout.spec(row, 1f)
            columnSpec = GridLayout.spec(col, 1f)
            setMargins(8, 8, 8, 8)
        }
    }

    fun setValue(value: Int) {
        // display the text if the tile has a value
        text = if (value > 0) value.toString() else ""

        // extract the bg color depending on tile value
        val backgroundColor = resources.getColor(getTileColorRes(value), context.theme)
        background.setTint(backgroundColor)

        // 2 and 4 tiles have a white text Color
        val textColor = resources.getColor(getTextColorRes(value), context.theme)
        setTextColor(textColor)
    }

    private fun getTileColorRes(value: Int): Int = when (value) {
        0 -> R.color.tile_bg
        2 -> R.color.tile_2
        4 -> R.color.tile_4
        8 -> R.color.tile_8
        16 -> R.color.tile_16
        32 -> R.color.tile_32
        64 -> R.color.tile_64
        128 -> R.color.tile_128
        256 -> R.color.tile_256
        512 -> R.color.tile_512
        1024 -> R.color.tile_1024
        2048 -> R.color.tile_2048
        else -> R.color.tile_super
    }

    private fun getTextColorRes(value: Int): Int = if (value <= 4) R.color.text_dark else R.color.text_light
}

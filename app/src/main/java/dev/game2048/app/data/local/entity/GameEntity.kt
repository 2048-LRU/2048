package dev.game2048.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_state")
data class GameEntity(
    @PrimaryKey val id: Int = 1,
    val board: String,
    val score: Int,
    val winTarget: Int,
    val state: String,
    val history: String
)


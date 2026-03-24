package dev.game2048.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "game_stats")
data class GameStatsEntity(
    @PrimaryKey val id: Int = 1,
    val bestScore: Int,
    val totalScore: Int,
    val topTile: Int,

    val wins: Int,
    val losses: Int
)

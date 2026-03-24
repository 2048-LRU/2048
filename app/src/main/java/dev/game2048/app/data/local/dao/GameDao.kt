package dev.game2048.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.game2048.app.data.local.entity.GameEntity
import dev.game2048.app.data.local.entity.GameStatsEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM game_state WHERE id = 1")
    suspend fun getGameState(): GameEntity?

    @Query("SELECT * from game_stats WHERE id=1")
    suspend fun getGameStats(): GameStatsEntity?

    @Upsert
    suspend fun saveGameState(entity: GameEntity)

    @Upsert
    suspend fun saveGameStats(entity: GameStatsEntity)
}

package dev.game2048.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.game2048.app.data.local.converters.GameConverters
import dev.game2048.app.data.local.dao.GameDao
import dev.game2048.app.data.local.entity.GameEntity
import dev.game2048.app.data.local.entity.GameStatsEntity

@Database(entities = [GameEntity::class, GameStatsEntity::class], version = 3, exportSchema = false)
@TypeConverters(GameConverters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}

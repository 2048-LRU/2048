package dev.game2048.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.game2048.app.data.local.dao.GameDao
import dev.game2048.app.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}


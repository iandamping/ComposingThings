package com.junemon.compose_stable.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StopwatchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StopwatchDatabase : RoomDatabase() {

    abstract fun stopwatchDao(): StopwatchDao
}
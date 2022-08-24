package com.junemon.compose_stable.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StopwatchDao {

    @Query("SELECT * FROM stopwatch_table")
    fun loadLapTime(): Flow<List<StopwatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLapTime(vararg data: StopwatchEntity)

    @Query("DELETE FROM stopwatch_table")
    suspend fun deleteAllLapTime()
}
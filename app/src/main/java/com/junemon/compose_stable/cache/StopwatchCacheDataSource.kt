package com.junemon.compose_stable.cache

import kotlinx.coroutines.flow.Flow

interface StopwatchCacheDataSource {

    fun loadLapTime(): Flow<List<StopwatchEntity>>

    suspend fun insertLapTime(data: String)

    suspend fun deleteAllLapTime()
}
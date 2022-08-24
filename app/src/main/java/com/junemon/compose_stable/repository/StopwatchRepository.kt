package com.junemon.compose_stable.repository

import kotlinx.coroutines.flow.Flow

interface StopwatchRepository {

    fun loadLapTime(): Flow<List<String>>

    suspend fun insertLapTime(data: String)

    suspend fun deleteAllLapTime()
}
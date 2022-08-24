package com.junemon.compose_stable.repository

import com.junemon.compose_stable.cache.StopwatchCacheDataSource
import com.junemon.compose_stable.cache.StopwatchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StopwatchRepositoryImpl @Inject constructor(private val cacheDataSource: StopwatchCacheDataSource) :
    StopwatchRepository {
    override fun loadLapTime(): Flow<List<String>> {
        return cacheDataSource.loadLapTime().map { it.mapListToString() }
    }

    override suspend fun insertLapTime(data: String) {
        cacheDataSource.insertLapTime(data)
    }

    override suspend fun deleteAllLapTime() {
        cacheDataSource.deleteAllLapTime()
    }
}

fun StopwatchEntity.mapToString(): String = this.lapTime
fun List<StopwatchEntity>.mapListToString() = map { it.mapToString() }
package com.junemon.compose_stable.cache

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StopwatchCacheDataSourceImpl @Inject constructor(private val dao: StopwatchDao) :
    StopwatchCacheDataSource {
    override fun loadLapTime(): Flow<List<StopwatchEntity>> {
        return dao.loadLapTime()
    }

    override suspend fun insertLapTime(data: String) {
        dao.insertLapTime(StopwatchEntity(stopwatchId = null, lapTime = data))
    }

    override suspend fun deleteAllLapTime() {
        dao.deleteAllLapTime()
    }
}
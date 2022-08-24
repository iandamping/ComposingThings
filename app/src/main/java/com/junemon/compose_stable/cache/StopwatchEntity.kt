package com.junemon.compose_stable.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stopwatch_table")
data class StopwatchEntity(
    @PrimaryKey
    @ColumnInfo(name = "stopwatch_id") val stopwatchId: Int?,
    @ColumnInfo(name = "lap_time") val lapTime: String,
)
package com.junemon.compose_stable.core

import com.junemon.compose_stable.DataSource
import com.junemon.compose_stable.DynamicContent
import com.junemon.compose_stable.PushFirebaseStatus
import kotlinx.coroutines.flow.Flow


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface RemoteDataSource {

    fun getDynamicContent(): Flow<DataSource<List<DynamicContent>>>

    suspend fun pushDynamicContent(
        data: List<DynamicContent>
    ): PushFirebaseStatus<Nothing>
}
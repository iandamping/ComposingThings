package com.junemon.compose_stable.core

import androidx.lifecycle.LiveData
import com.junemon.compose_stable.DataSource
import com.junemon.compose_stable.DomainSource
import com.junemon.compose_stable.DynamicContent
import com.junemon.compose_stable.PushFirebaseStatus
import kotlinx.coroutines.flow.Flow


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface Repository {

    fun getDynamicContent(): Flow<DomainSource<List<DynamicContent>>>

    fun pushDynamicContent(
        data: List<DynamicContent>
    ): Flow<PushFirebaseStatus<Nothing>>
}
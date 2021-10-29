package com.junemon.compose_stable.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.junemon.compose_stable.DataSource
import com.junemon.compose_stable.DomainSource
import com.junemon.compose_stable.DynamicContent
import com.junemon.compose_stable.PushFirebaseStatus
import com.junemon.compose_stable.util.Constant.ERROR_PUSH_FIREBASE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class RepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) : Repository {
    override fun getDynamicContent(): Flow<DomainSource<List<DynamicContent>>> {
        return dataSource.getDynamicContent().map { value ->
            when (value) {
                is DataSource.Result -> DomainSource.Result(value.data)
                is DataSource.Fail -> DomainSource.Fail(ERROR_PUSH_FIREBASE)
            }
        }
    }

    override fun pushDynamicContent(data: List<DynamicContent>): Flow<PushFirebaseStatus<Nothing>> {
        return flow {
            emit(dataSource.pushDynamicContent(data = data))
        }
    }
}
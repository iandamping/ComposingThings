package com.junemon.compose_stable.core.data.datasource.remote

import com.junemon.compose_stable.core.data.model.Results
import retrofit2.Response

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface BaseSource {
    suspend fun <T> oneShotCalls(call: suspend () -> Response<T>): Results<T>
}
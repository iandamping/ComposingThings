package com.junemon.compose_stable.core.data.datasource

import com.junemon.compose_stable.core.data.datasource.remote.ApiInterface
import com.junemon.compose_stable.core.data.datasource.remote.BaseSource
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.API_KEY
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.data.model.Results
import com.junemon.compose_stable.core.data.model.response.NewsResponse
import javax.inject.Inject

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class NewsRemoteDataSourceImpl @Inject constructor(
    baseSource: BaseSource,
    private val api: ApiInterface,
) : NewsRemoteDataSource, BaseSource by baseSource {

    override suspend fun getNews(): DataSourceResult<List<NewsResponse>> {
        return when (val response = oneShotCalls { api.getNews(apiKey = API_KEY) }) {
            is Results.Error -> DataSourceResult.SourceError(response.exception)
            is Results.Success -> when (response.data.status) {
                "ok" -> {
                    DataSourceResult.SourceValue(
                        response.data.articles
                    )
                }
                else -> DataSourceResult.SourceError(Exception(NETWORK_ERROR))
            }
        }
    }
}
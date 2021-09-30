package com.junemon.compose_stable.core.data.datasource

import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.data.model.response.NewsResponse

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface NewsRemoteDataSource {

    suspend fun getNews(): DataSourceResult<List<NewsResponse>>

    suspend fun searchNews(query:String): DataSourceResult<List<NewsResponse>>

}
package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.data.model.response.NewsResponse
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface NewsRepository {

    fun getNews(): Flow<DomainResult<List<News>>>

    fun searchNews(query:String): Flow<DomainResult<List<News>>>
}
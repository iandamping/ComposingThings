package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.news.News

interface NewsRepository {

    suspend fun getNews(): Results<List<News>>

    suspend fun searchNews(query: String): Results<List<News>>

}
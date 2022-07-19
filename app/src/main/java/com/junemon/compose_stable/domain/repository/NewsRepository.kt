package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.news.News

interface NewsRepository {

    suspend fun getNews(): Results<List<News>>

    suspend fun searchNews(query: String): Results<List<News>>

}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.datasource.response.news.NewsResponse

interface NewsRemoteDataSource {

    suspend fun getNews(): List<NewsResponse>

    suspend fun searchNews(query: String): List<NewsResponse>
}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.network.NewsApi
import com.junemon.compose_stable.response.news.NewsResponse

class NewsRemoteDataSourceImpl(private val api: NewsApi) : NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        return api.getNews().articles
    }

    override suspend fun searchNews(query: String): List<NewsResponse> {
        return api.searchNews(searchQuery = query).articles
    }
}
package com.junemon.compose_stable.core.datasource

import com.junemon.compose_stable.core.datasource.network.NewsApi
import com.junemon.compose_stable.core.datasource.response.news.NewsResponse
import com.junemon.compose_stable.di.NewsApiInterface
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(@NewsApiInterface private val api: NewsApi) :
    NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        return try {
            api.getNews().articles
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchNews(query: String): List<NewsResponse> {
        return try {
            api.searchNews(searchQuery = query).articles
        } catch (e: Exception) {
            throw e
        }
    }
}
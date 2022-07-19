package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.datasource.NewsRemoteDataSource
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.news.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val dataSource: NewsRemoteDataSource) :
    NewsRepository {

    override suspend fun getNews(): Results<List<News>> {
        return try {
            Results.Success(dataSource.getNews().map { it.toDomain() })
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun searchNews(query: String): Results<List<News>> {
        return try {
            Results.Success(dataSource.searchNews(query).map { it.toDomain() })
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }
}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.network.NewsApi
import com.junemon.compose_stable.datasource.response.news.NewsResponse
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val api: NewsApi) : NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        return try{
            api.getNews().articles
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun searchNews(query: String): List<NewsResponse> {
        return try {
            api.searchNews(searchQuery = query).articles
        }catch (e:Exception){
            throw e
        }
    }
}
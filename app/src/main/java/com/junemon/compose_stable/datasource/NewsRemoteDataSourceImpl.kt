package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.network.NetworkConstant
import com.junemon.compose_stable.network.NewsApi
import com.junemon.compose_stable.response.news.NewsResponse
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val api: NewsApi) : NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        return try{
            api.getNews().articles
        }catch (e:Exception){
            throw Exception(NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun searchNews(query: String): List<NewsResponse> {
        return try {
            api.searchNews(searchQuery = query).articles
        }catch (e:Exception){
            throw Exception(NetworkConstant.NETWORK_ERROR)
        }
    }
}
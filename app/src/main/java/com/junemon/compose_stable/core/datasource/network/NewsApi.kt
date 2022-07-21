package com.junemon.compose_stable.core.datasource.network

import com.junemon.compose_stable.core.datasource.network.NetworkConstant.GET_TOP_HEADLINES
import com.junemon.compose_stable.core.datasource.network.NetworkConstant.NEWS_API_KEY
import com.junemon.compose_stable.core.datasource.response.news.NewsBaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(GET_TOP_HEADLINES)
    suspend fun getNews(
        @Query("apiKey") apiKey: String = NEWS_API_KEY, @Query("country") country: String = "id"
    ): NewsBaseResponse

    @GET(GET_TOP_HEADLINES)
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("country") country: String = "id",
        @Query("q") searchQuery: String
    ): NewsBaseResponse
}
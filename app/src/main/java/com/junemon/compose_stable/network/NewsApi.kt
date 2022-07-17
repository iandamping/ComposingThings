package com.junemon.compose_stable.network

import com.junemon.compose_stable.network.NetworkConstant.GET_TOP_HEADLINES
import com.junemon.compose_stable.network.NetworkConstant.NEWS_API_KEY
import com.junemon.compose_stable.response.news.NewsBaseResponse
import com.junemon.compose_stable.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(GET_TOP_HEADLINES)
    suspend fun getNews(
        @Query("apiKey") apiKey: String = NEWS_API_KEY, @Query("country") country: String = "id"
    ): NewsBaseResponse<NewsResponse>

    @GET(GET_TOP_HEADLINES)
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("country") country: String = "id",
        @Query("q") searchQuery: String
    ): NewsBaseResponse<NewsResponse>
}
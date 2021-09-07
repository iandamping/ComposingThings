package com.junemon.compose_stable.core.data.datasource.remote

import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.GET_TOP_HEADLINES
import com.junemon.compose_stable.core.data.model.response.BaseResponse
import com.junemon.compose_stable.core.data.model.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {

    @GET(GET_TOP_HEADLINES)
    suspend fun getNews(
        @Query("apiKey") apiKey: String, @Query("country") country:String = "id"
    ): Response<BaseResponse<NewsResponse>>
}
package com.junemon.compose_stable.datasource.network

import com.junemon.compose_stable.datasource.network.NetworkConstant.DETAIL_MOVIE
import com.junemon.compose_stable.datasource.network.NetworkConstant.MOVIE_API_KEY
import com.junemon.compose_stable.datasource.network.NetworkConstant.POPULAR_MOVIE
import com.junemon.compose_stable.datasource.response.movie.MovieDetailResponse
import com.junemon.compose_stable.datasource.response.movie.MovieMainResponse
import com.junemon.compose_stable.datasource.response.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(POPULAR_MOVIE)
    suspend fun getPopularMovie(@Query("api_key") apiKey: String = MOVIE_API_KEY): MovieMainResponse

    @GET("$DETAIL_MOVIE{movie}")
    suspend fun getDetailMovie(
        @Path("movie") movieId: Int,
        @Query("api_key") apiKey: String = MOVIE_API_KEY
    ): MovieDetailResponse


}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.response.movie.MovieDetailResponse
import com.junemon.compose_stable.response.movie.MovieResponse

interface MovieRemoteDataSource {

    suspend fun getMovie(): List<MovieResponse>

    suspend fun getDetailMovie(movieId: Int): MovieDetailResponse

}
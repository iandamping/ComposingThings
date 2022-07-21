package com.junemon.compose_stable.core.datasource

import com.junemon.compose_stable.core.datasource.response.movie.MovieDetailResponse
import com.junemon.compose_stable.core.datasource.response.movie.MovieResponse

interface MovieRemoteDataSource {

    suspend fun getMovie(): List<MovieResponse>

    suspend fun getDetailMovie(movieId: Int): MovieDetailResponse

}
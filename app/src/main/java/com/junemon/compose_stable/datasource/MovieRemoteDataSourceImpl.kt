package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.network.MovieApi
import com.junemon.compose_stable.response.movie.MovieDetailResponse
import com.junemon.compose_stable.response.movie.MovieResponse
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val api: MovieApi) : MovieRemoteDataSource {
    override suspend fun getMovie(): List<MovieResponse> {
        return api.getPopularMovie().results
    }

    override suspend fun getDetailMovie(movieId: Int): MovieDetailResponse {
        return api.getDetailMovie(movieId)
    }
}
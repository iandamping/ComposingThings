package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.datasource.network.MovieApi
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.response.movie.MovieDetailResponse
import com.junemon.compose_stable.datasource.response.movie.MovieResponse
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val api: MovieApi) : MovieRemoteDataSource {
    override suspend fun getMovie(): List<MovieResponse> {
        return try {
            api.getPopularMovie().results
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getDetailMovie(movieId: Int): MovieDetailResponse {
        return try {
            api.getDetailMovie(movieId)
        }catch (e:Exception){
            throw e
        }
    }
}
package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.datasource.MovieRemoteDataSource
import com.junemon.compose_stable.core.datasource.network.NetworkConstant
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.movie.Movie
import com.junemon.compose_stable.core.domain.model.movie.MovieDetail
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovie(): Results<List<Movie>> {
        return try {
            Results.Success(movieRemoteDataSource.getMovie().map { it.toDomain() })
        } catch (e: Exception) {
            Results.Error(e.localizedMessage ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getDetailMovie(movieId: Int): Results<MovieDetail> {
        return try {
            Results.Success(movieRemoteDataSource.getDetailMovie(movieId).toDomain())
        } catch (e: Exception) {
            Results.Error(e.localizedMessage ?: NetworkConstant.NETWORK_ERROR)
        }
    }

}
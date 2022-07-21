package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.movie.Movie
import com.junemon.compose_stable.core.domain.model.movie.MovieDetail

interface MovieRepository {

    suspend fun getMovie(): Results<List<Movie>>

    suspend fun getDetailMovie(movieId: Int): Results<MovieDetail>
}
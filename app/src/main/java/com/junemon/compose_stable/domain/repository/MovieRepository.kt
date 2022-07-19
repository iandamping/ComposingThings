package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.movie.Movie
import com.junemon.compose_stable.domain.model.movie.MovieDetail

interface MovieRepository {

    suspend fun getMovie(): Results<List<Movie>>

    suspend fun getDetailMovie(movieId: Int): Results<MovieDetail>
}
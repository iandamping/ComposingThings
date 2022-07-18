package com.junemon.compose_stable

import com.junemon.compose_stable.response.movie.MovieDetailResponse
import com.junemon.compose_stable.response.movie.MovieResponse

object DummyMovies {

    val DUMMY_MOVIE_1 = MovieResponse(
        1,
        1.1,
        "a",
        "a",
        "a",
        "a",
        "a",
        1,
        false,
        1.1,
        "a",
        listOf(1, 2, 3),
        false,
        "a"
    )
    val DUMMY_MOVIE_2 = MovieResponse(
        1,
        1.1,
        "a",
        "a",
        "a",
        "a",
        "a",
        1,
        false,
        1.1,
        "a",
        listOf(1, 2, 3),
        false,
        "a"
    )
    val DUMMY_MOVIE_3 = MovieResponse(
        1,
        1.1,
        "a",
        "a",
        "a",
        "a",
        "a",
        1,
        false,
        1.1,
        "a",
        listOf(1, 2, 3),
        false,
        "a"
    )
    val DUMMY_LIST_MOVIE = listOf(DUMMY_MOVIE_1, DUMMY_MOVIE_2, DUMMY_MOVIE_3)

    val DUMMY_DETAIL_MOVIE = MovieDetailResponse(
        "a",
        1,
        1,
        "a",
        "a",
        "a",
        "a",
        1.1,
        false,
        "a",
        "a",
        "a",
        1.1,
        "a",
        "a",
        1,
        "a",
        false,
        1
    )
}
package com.junemon.compose_stable.presentation.state.movie

import com.junemon.compose_stable.domain.model.movie.MovieDetail

data class DetailMovieState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: MovieDetail?,
) {
    companion object {
        fun initial() = DetailMovieState(
            isLoading = true,
            failedMessage = "",
            data = null
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
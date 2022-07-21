package com.junemon.compose_stable.core.presentation.state.movie

import com.junemon.compose_stable.core.domain.model.movie.MovieDetail


data class MovieDetailState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: MovieDetail?,
) {
    companion object {
        fun initial() = MovieDetailState(
            isLoading = true,
            failedMessage = "",
            data = null
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
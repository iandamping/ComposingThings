package com.junemon.compose_stable.core.presentation.state.movie

import com.junemon.compose_stable.core.domain.model.movie.Movie
import javax.annotation.concurrent.Immutable

@Immutable
data class MovieState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<Movie>,
) {
    companion object {
        fun initial() = MovieState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
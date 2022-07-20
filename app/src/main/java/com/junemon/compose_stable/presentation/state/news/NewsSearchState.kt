package com.junemon.compose_stable.presentation.state.news

import com.junemon.compose_stable.domain.model.news.News
import javax.annotation.concurrent.Immutable

@Immutable
data class NewsSearchState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<News>,
) {

    companion object {
        fun initial() = NewsSearchState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
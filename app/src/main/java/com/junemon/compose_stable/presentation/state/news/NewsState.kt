package com.junemon.compose_stable.presentation.state.news

import com.junemon.compose_stable.domain.model.news.News
import javax.annotation.concurrent.Immutable

/**
 * Created by Ian Damping on 21,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Immutable
data class NewsState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<News>,
) {

    companion object {
        fun initial() = NewsState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
package com.junemon.compose_stable.core.presentation.model

import com.junemon.compose_stable.base.MviUiEvent
import com.junemon.compose_stable.base.MviUiState
import com.junemon.compose_stable.core.domain.model.response.News
import javax.annotation.concurrent.Immutable


/**
 * Created by Ian Damping on 24,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Immutable
sealed class SearchScreenUiEvent : MviUiEvent {
    data class ShowData(val items: List<News>) : SearchScreenUiEvent()
    data class FailedMessage(val message: String) : SearchScreenUiEvent()
    object Idle : SearchScreenUiEvent()
}


@Immutable
data class SearchScreenState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<News>,
) : MviUiState {

    companion object {
        fun initial() = SearchScreenState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size} failedMessage: $failedMessage"
    }
}
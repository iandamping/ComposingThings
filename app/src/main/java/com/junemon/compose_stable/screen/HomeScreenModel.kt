package com.junemon.compose_stable.screen

import com.junemon.compose_stable.base.MviUiEvent
import com.junemon.compose_stable.base.MviUiState
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import javax.annotation.concurrent.Immutable


/**
 * Created by Ian Damping on 21,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Immutable
sealed class HomeScreenUiEvent : MviUiEvent {
    data class ShowData(val items: List<PokemonDetail>) : HomeScreenUiEvent()
    data class FailedMessage(val message: String) : HomeScreenUiEvent()
}


@Immutable
data class HomeScreenState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<PokemonDetail>,
) : MviUiState {

    companion object {
        fun initial() = HomeScreenState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}
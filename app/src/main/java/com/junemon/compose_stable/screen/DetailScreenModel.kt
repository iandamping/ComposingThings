package com.junemon.compose_stable.screen

import com.junemon.compose_stable.base.MviUiState
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import javax.annotation.concurrent.Immutable

@Immutable
data class DetailScreenCharacteristicState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: String,
) {

    companion object {
        fun initial() = DetailScreenCharacteristicState(
            isLoading = true,
            failedMessage = "",
            data = ""
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failed message : $failedMessage"
    }
}

@Immutable
data class DetailScreenAreaState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<String>,
) : MviUiState {

    companion object {
        fun initial() = DetailScreenAreaState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}, failed message : $failedMessage"
    }
}

@Immutable
data class DetailPokemonStatState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: PokemonDetail?,
) {

    companion object {
        fun initial() = DetailPokemonStatState(
            isLoading = true,
            failedMessage = "",
            data = null
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data"
    }
}
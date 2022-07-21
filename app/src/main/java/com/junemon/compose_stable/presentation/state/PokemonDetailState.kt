package com.junemon.compose_stable.presentation.state

import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail

data class PokemonDetailState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: PokemonDetail?
) {
    companion object {
        fun initial() = PokemonDetailState(
            isLoading = true,
            failedMessage = "",
            data = null
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
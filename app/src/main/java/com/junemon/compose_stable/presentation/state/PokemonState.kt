package com.junemon.compose_stable.presentation.state

import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail

data class PokemonState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<PokemonDetail>,
) {
    companion object {
        fun initial() = PokemonState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}
package com.junemon.compose_stable.presentation

data class PokemonDetailLocationState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<String>
) {
    companion object {
        fun initial() = PokemonDetailLocationState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}

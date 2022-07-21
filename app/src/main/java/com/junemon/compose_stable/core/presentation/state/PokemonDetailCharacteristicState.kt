package com.junemon.compose_stable.core.presentation.state

data class PokemonDetailCharacteristicState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: String?
) {
    companion object {
        fun initial() = PokemonDetailCharacteristicState(
            isLoading = true,
            failedMessage = "",
            data = null
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data: $data, failedMessage: $failedMessage"
    }
}

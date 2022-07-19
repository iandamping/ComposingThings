package com.junemon.compose_stable.datasource.response.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonCharacteristicResponse(
    @field:SerializedName("descriptions") val descriptions: List<ItemPokemonCharacteristicResponse>
)

data class ItemPokemonCharacteristicResponse(
    @field:SerializedName("description") val description: String,
)
package com.junemon.compose_stable.core.datasource.response.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResultsResponse(
    @SerializedName("url") val pokemonUrl: String
)
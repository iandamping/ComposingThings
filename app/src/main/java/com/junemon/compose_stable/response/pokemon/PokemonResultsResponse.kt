package com.junemon.compose_stable.response.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResultsResponse(
    @SerializedName("url") val pokemonUrl: String
)
package com.junemon.compose_stable.core.data.datasource.response

import com.google.gson.annotations.SerializedName

data class PokemonResultsResponse(
    @SerializedName("url") val pokemonUrl: String
)
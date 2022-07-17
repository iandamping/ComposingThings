package com.junemon.compose_stable.response.pokemon

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonMainResponse(
    @SerializedName("results") val pokemonResults: List<PokemonResultsResponse>
)


package com.junemon.compose_stable.response.pokemon

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonTypesResponse(
    @SerializedName("type") val type: PokemonTypeSingleResponse,
)

data class PokemonTypeSingleResponse(
    @SerializedName("name") val typeName: String
)
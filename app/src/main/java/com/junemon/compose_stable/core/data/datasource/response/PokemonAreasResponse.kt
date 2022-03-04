package com.junemon.compose_stable.core.data.datasource.response

import com.google.gson.annotations.SerializedName

data class PokemonAreasResponse(
    @field:SerializedName("location_area") val area:PokemonAreasName
)

data class PokemonAreasName(
    @field:SerializedName("name") val name:String
)

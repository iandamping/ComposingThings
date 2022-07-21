package com.junemon.compose_stable.core.datasource.response.pokemon

import com.google.gson.annotations.SerializedName
import com.junemon.compose_stable.core.datasource.response.pokemon.mapper.checkEggGroupList
import com.junemon.compose_stable.core.domain.model.Dto
import com.junemon.compose_stable.core.domain.model.pokemon.PokemonDetailSpecies

/**
 * Created by Ian Damping on 10,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class PokemonSpeciesDetailResponse(
    @SerializedName("base_happiness") val pokemonHappines: Int,
    @SerializedName("capture_rate") val pokemonCaptureRate: Int,
    @SerializedName("color") val pokemonColor: PokemonSpeciesColorResponse,
    @SerializedName("egg_groups") val pokemonEggGroup: List<PokemonSpeciesEggGroupResponse>,
    @SerializedName("generation") val pokemonGeneration: PokemonGenerationResponse,
    @SerializedName("growth_rate") val pokemonGrowthRate: PokemonGrowthRateResponse,
    @SerializedName("habitat") val pokemonHabitat: PokemonHabitatResponse,
    @SerializedName("shape") val pokemonShape: PokemonShapeResponse,
) : Dto {

    override fun toDomain(): PokemonDetailSpecies {
        return PokemonDetailSpecies(
            happiness = pokemonHappines,
            captureRate = pokemonCaptureRate,
            color = pokemonColor.pokemonColor,
            eggGroup1 = pokemonEggGroup[0].eggName,
            eggGroup2 = pokemonEggGroup.checkEggGroupList(1, 1),
            generation = pokemonGeneration.pokemonGenerationLString,
            growthRate = pokemonGrowthRate.pokemonGrowthRate,
            habitat = pokemonHabitat.pokemonHabitat,
            shape = pokemonShape.pokemonShape
        )
    }
}


data class PokemonGenerationResponse(@SerializedName("name") val pokemonGenerationLString: String)

data class PokemonGrowthRateResponse(@SerializedName("name") val pokemonGrowthRate: String)

data class PokemonHabitatResponse(@SerializedName("name") val pokemonHabitat: String)

data class PokemonShapeResponse(@SerializedName("name") val pokemonShape: String)

data class PokemonSpeciesColorResponse(@SerializedName("name") val pokemonColor: String)

data class PokemonSpeciesEggGroupResponse(@SerializedName("name") val eggName: String)
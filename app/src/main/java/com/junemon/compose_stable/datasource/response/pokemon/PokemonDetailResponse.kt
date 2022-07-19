package com.junemon.compose_stable.datasource.response.pokemon

import com.google.gson.annotations.SerializedName
import com.junemon.compose_stable.datasource.response.pokemon.mapper.checkAbilitiesList
import com.junemon.compose_stable.datasource.response.pokemon.mapper.checkTypeList
import com.junemon.compose_stable.domain.model.Domain
import com.junemon.compose_stable.domain.model.Dto
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail
import com.junemon.compose_stable.domain.model.pokemon.PokemonStat
import com.junemon.compose_stable.util.PokemonConstant.ONE_SKILL_MONS
import com.junemon.compose_stable.util.PokemonConstant.ONE_TYPE_MONS
import java.util.*

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonDetailResponse(
    @SerializedName("id") val pokemonId: Int,
    @SerializedName("name") val pokemonName: String,
    @SerializedName("weight") val pokemonWeight: Int,
    @SerializedName("height") val pokemonHeight: Int,
    @SerializedName("sprites") val pokemonImage: PokemonSpritesResponse,
    @SerializedName("stats") val pokemonStats: List<PokemonBasicStatsResponse>,
    @SerializedName("types") val pokemonTypes: List<PokemonTypesResponse>,
    @SerializedName("abilities") val pokemonAbilities: List<PokemonAbilitiesResponse>,
    @SerializedName("species") val pokemonSpecies: PokemonSpeciesResultResponse,
) :Dto{

    override fun toDomain(): PokemonDetail {
        return PokemonDetail(
            pokemonId = pokemonId,
            pokemonWeight = pokemonWeight,
            pokemonHeight = pokemonHeight,
            pokemonName = pokemonName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            pokemonImage = pokemonImage.sprites.other.image,
            pokemonSmallImage1 = pokemonImage.smallImage1,
            pokemonSmallImage2 = pokemonImage.smallImage2,
            pokemonSmallImage3 = pokemonImage.smallImage3,
            pokemonSmallImage4 = pokemonImage.smallImage4,
            pokemonStat0 = pokemonStats[0].toDomain(),
            pokemonStat1 = pokemonStats[1].toDomain(),
            pokemonStat2 = pokemonStats[2].toDomain(),
            pokemonStat3 = pokemonStats[3].toDomain(),
            pokemonStat4 = pokemonStats[4].toDomain(),
            pokemonStat5 = pokemonStats[5].toDomain(),
            pokemonType0 = pokemonTypes[0].type.typeName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            pokemonType1 = pokemonTypes.checkTypeList(1, 1),
            pokemonAbility1 = pokemonAbilities[0].abilities.abilityName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            pokemonAbility2 = pokemonAbilities.checkAbilitiesList(1, 1),
            pokemonSpeciesUrl = pokemonSpecies.speciesUrl
        )
    }
}





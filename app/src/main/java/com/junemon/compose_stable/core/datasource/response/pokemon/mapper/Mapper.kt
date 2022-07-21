package com.junemon.compose_stable.core.datasource.response.pokemon.mapper

import com.junemon.compose_stable.core.datasource.response.pokemon.PokemonAbilitiesResponse
import com.junemon.compose_stable.core.datasource.response.pokemon.PokemonSpeciesEggGroupResponse
import com.junemon.compose_stable.core.datasource.response.pokemon.PokemonTypesResponse
import com.junemon.compose_stable.util.PokemonConstant
import java.util.*

fun List<PokemonTypesResponse>.checkTypeList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].type.typeName.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    } else PokemonConstant.ONE_TYPE_MONS

fun List<PokemonAbilitiesResponse>.checkAbilitiesList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].abilities.abilityName.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    } else PokemonConstant.ONE_SKILL_MONS

fun List<PokemonSpeciesEggGroupResponse>.checkEggGroupList(size: Int, position: Int): String =
    if (this.size > size) {
        this[position].eggName
    } else PokemonConstant.ONE_EGG_MONS

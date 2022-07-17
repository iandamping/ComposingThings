package com.junemon.compose_stable

import com.junemon.compose_stable.response.pokemon.*

object Dummy {
    val DUMMY_URL_POKEMON_RESULTS_1 = PokemonResultsResponse("a")
    val DUMMY_URL_POKEMON_RESULTS_2 = PokemonResultsResponse("a")
    val DUMMY_URL_POKEMON_RESULTS_3 = PokemonResultsResponse("a")
    val DUMMY_POKEMON_STAT_NAME = PokemonStatNameResponse("a")
    val DUMMY_POKEMON_BASIC_STAT_1 = PokemonBasicStatsResponse(1, DUMMY_POKEMON_STAT_NAME)
    val DUMMY_POKEMON_BASIC_STAT_2 = PokemonBasicStatsResponse(2, DUMMY_POKEMON_STAT_NAME)
    val DUMMY_POKEMON_BASIC_STAT_3 = PokemonBasicStatsResponse(3, DUMMY_POKEMON_STAT_NAME)
    val DUMMY_POKEMON_SINGLE_TYPE_1 = PokemonTypeSingleResponse("a")
    val DUMMY_POKEMON_TYPE_1 = PokemonTypesResponse(DUMMY_POKEMON_SINGLE_TYPE_1)
    val DUMMY_POKEMON_TYPE_2 = PokemonTypesResponse(DUMMY_POKEMON_SINGLE_TYPE_1)
    val DUMMY_POKEMON_TYPE_3 = PokemonTypesResponse(DUMMY_POKEMON_SINGLE_TYPE_1)
    val DUMMY_LIST_POKEMON_TYPE = listOf(
        DUMMY_POKEMON_TYPE_1, DUMMY_POKEMON_TYPE_2,
        DUMMY_POKEMON_TYPE_3
    )
    val DUMMY_POKEMON_ABILITY_NAME_1 = PokemonAbilitiesNameResponse("a")
    val DUMMY_POKEMON_ABILITY_1 = PokemonAbilitiesResponse(DUMMY_POKEMON_ABILITY_NAME_1)
    val DUMMY_POKEMON_ABILITY_2 = PokemonAbilitiesResponse(DUMMY_POKEMON_ABILITY_NAME_1)
    val DUMMY_POKEMON_ABILITY_3 = PokemonAbilitiesResponse(DUMMY_POKEMON_ABILITY_NAME_1)
    val DUMMY_POKEMON_SPECIAL_1 = PokemonSpeciesResultResponse("a")
    val DUMMY_LIST_POKEMON_ABILITY = listOf(
        DUMMY_POKEMON_ABILITY_1, DUMMY_POKEMON_ABILITY_2,
        DUMMY_POKEMON_ABILITY_3
    )
    val DUMMY_LIST_POKEMON_BASIC_STAT = listOf(
        DUMMY_POKEMON_BASIC_STAT_1,
        DUMMY_POKEMON_BASIC_STAT_2, DUMMY_POKEMON_BASIC_STAT_3
    )
    val DUMMY_POKEMON_MAIN_RESPONSE = PokemonMainResponse(
        listOf(
            DUMMY_URL_POKEMON_RESULTS_1,
            DUMMY_URL_POKEMON_RESULTS_2, DUMMY_URL_POKEMON_RESULTS_3
        )
    )
    val DUMMY_POKEMON_OFFICIAL_ARTWORK = PokemonOfficialArtworkResponse("a")
    val DUMMY_POKEMON_OTHER_SPRITE = PokemonSpritesOtherResponse(DUMMY_POKEMON_OFFICIAL_ARTWORK)
    val DUMMY_POKEMON_SPRITE =
        PokemonSpritesResponse(DUMMY_POKEMON_OTHER_SPRITE, "a", "a", "a", "a")
    val DUMMY_POKEMON_DETAIL =
        PokemonDetailResponse(
            1,
            "a",
            1,
            1,
            DUMMY_POKEMON_SPRITE,
            DUMMY_LIST_POKEMON_BASIC_STAT,
            DUMMY_LIST_POKEMON_TYPE,
            DUMMY_LIST_POKEMON_ABILITY,
            DUMMY_POKEMON_SPECIAL_1
        )

}
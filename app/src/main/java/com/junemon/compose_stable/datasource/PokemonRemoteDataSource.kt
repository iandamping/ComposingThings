package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.datasource.response.pokemon.PokemonDetailResponse
import com.junemon.compose_stable.datasource.response.pokemon.PokemonResultsResponse
import com.junemon.compose_stable.datasource.response.pokemon.PokemonSpeciesDetailResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemon(): List<PokemonResultsResponse>

    suspend fun getDetailPokemonByUrl(url: String): PokemonDetailResponse

    suspend fun getDetailPokemonCharacteristic(id: Int): String

    suspend fun getPokemonLocationAreas(id: Int): List<String>

    suspend fun getDetailPokemonById(id: Int): PokemonDetailResponse

    suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse
}
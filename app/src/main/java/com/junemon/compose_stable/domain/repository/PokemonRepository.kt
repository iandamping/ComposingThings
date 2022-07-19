package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetailSpecies

interface PokemonRepository {

    suspend fun getPokemon(): Results<List<PokemonDetail>>

    suspend fun getDetailSpeciesPokemon(url: String): Results<PokemonDetailSpecies>

    suspend fun getDetailPokemonCharacteristic(id: Int): Results<String>

    suspend fun getPokemonLocationAreas(id: Int): Results<List<String>>

    suspend fun getPokemonById(id: Int): Results<PokemonDetail>
}
package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRepository {

    suspend fun getPokemon(): DomainResult<List<PokemonDetail>>

    suspend fun getDetailSpeciesPokemon(url: String): DomainResult<PokemonDetailSpecies>

    suspend fun getDetailPokemonCharacteristic(id: Int): DomainResult<String>

    suspend fun getPokemonLocationAreas(id: Int): DomainResult<List<String>>

    suspend fun getPokemonById(id: Int): DomainResult<PokemonDetail>


}
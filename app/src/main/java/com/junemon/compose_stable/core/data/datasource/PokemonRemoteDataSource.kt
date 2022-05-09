package com.junemon.compose_stable.core.data.datasource

import com.junemon.compose_stable.core.data.datasource.response.*
import com.junemon.compose_stable.core.data.model.DataSourceResult

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRemoteDataSource {

    suspend fun getPokemon(): DataSourceResult<List<PokemonResultsResponse>>

    suspend fun getDetailPokemon(url: String): PokemonDetailResponse

    suspend fun getDetailPokemonCharacteristic(id: Int): DataSourceResult<String>

    suspend fun getPokemonLocationAreas(id: Int): DataSourceResult<List<String>>

    suspend fun getPokemonById(id: Int): DataSourceResult<PokemonDetailResponse>

    suspend fun getDetailSpeciesPokemon(url: String): DataSourceResult<PokemonSpeciesDetailResponse>
}
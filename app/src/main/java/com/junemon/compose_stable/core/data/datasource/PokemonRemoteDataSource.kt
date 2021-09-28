package com.junemon.compose_stable.core.data.datasource

import com.junemon.compose_stable.core.data.datasource.response.PokemonDetailResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonResultsResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonSpeciesDetailResponse
import com.junemon.compose_stable.core.data.model.DataSourceResult

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRemoteDataSource {

    suspend fun getPokemon(): DataSourceResult<List<PokemonResultsResponse>>

    suspend fun getDetailPokemon(url: String): PokemonDetailResponse

    suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse
}
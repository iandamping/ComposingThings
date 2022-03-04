package com.junemon.compose_stable.core.domain.repository

import com.junemon.compose_stable.core.data.datasource.response.PokemonAreasResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonCharacteristicResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonDetailResponse
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonRepository {

    fun getPokemon(): Flow<DomainResult<List<PokemonDetail>>>

    fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies>

    fun getDetailPokemonCharacteristic(id: Int): Flow<DomainResult<String>>

    fun getPokemonLocationAreas(id: Int): Flow<DomainResult<List<String>>>

    fun getPokemonById(id: Int): Flow<DomainResult<PokemonDetail>>


}
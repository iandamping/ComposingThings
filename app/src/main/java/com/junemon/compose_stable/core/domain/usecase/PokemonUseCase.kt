package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.data.datasource.response.PokemonAreasResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonCharacteristicResponse
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import com.junemon.compose_stable.core.presentation.model.UiState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonUseCase {

    suspend fun getPokemon(): UiState<List<PokemonDetail>>

    suspend fun getDetailSpeciesPokemon(url: String): UiState<PokemonDetailSpecies>

    suspend fun getDetailPokemonCharacteristic(id: Int): UiState<String>

    suspend fun getPokemonLocationAreas(id: Int): UiState<List<String>>

    suspend fun getPokemonById(id: Int): UiState<PokemonDetail>
}
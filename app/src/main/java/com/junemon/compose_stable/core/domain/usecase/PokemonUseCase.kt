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

    fun getPokemon(): Flow<UiState<List<PokemonDetail>>>

    fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies>

    fun getDetailPokemonCharacteristic(id: Int): Flow<UiState<String>>

    fun getPokemonLocationAreas(id: Int): Flow<UiState<List<String>>>

    fun getPokemonById(id: Int): Flow<UiState<PokemonDetail>>
}
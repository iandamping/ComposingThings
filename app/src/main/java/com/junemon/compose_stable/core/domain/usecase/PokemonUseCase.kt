package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.UiState
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PokemonUseCase {


    fun getPokemon(): Flow<UiState<List<PokemonDetail>>>

    fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies>
}
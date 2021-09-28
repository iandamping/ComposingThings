package com.junemon.compose_stable.core.domain.usecase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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

    @Composable
    fun getPokemon(): State<UiState<List<PokemonDetail>>>

    fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies>
}
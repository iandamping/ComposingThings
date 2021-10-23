package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import com.junemon.compose_stable.core.presentation.model.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonUseCaseImpl @Inject constructor(private val repository: PokemonRepository) :
    PokemonUseCase {

    override fun getPokemon(): Flow<UiState<List<PokemonDetail>>> {
        return repository.getPokemon().map {
            when (it) {
                is DomainResult.Error -> UiState.Error(it.message)
                is DomainResult.Content -> UiState.Content(it.data)
            }
        }
    }

    override fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies> {
        return repository.getDetailSpeciesPokemon(url = url)
    }

}
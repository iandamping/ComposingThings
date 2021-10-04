package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.UiState
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 10,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonUseCaseImpl @Inject constructor(private val repository: PokemonRepository) :
    PokemonUseCase {

    override fun getPokemon(): Flow<UiState<List<PokemonDetail>>> {
        return repository.getPokemon()
    }

    override fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies> {
        return repository.getDetailSpeciesPokemon(url = url)
    }

}
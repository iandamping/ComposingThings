package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.data.datasource.cache.PokemonEntity
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
    override val getCachedPokemon: Flow<UiState<List<PokemonEntity>>>
        get() =
            repository.getCachedPokemon.map {
                when (it) {
                    is DomainResult.Error -> UiState.Error(it.message)
                    is DomainResult.Content -> UiState.Content(it.data)
                }
            }


    override suspend fun getRemotePokemon(): UiState<List<PokemonDetail>> {
        return when (val it = repository.getRemotePokemon()) {
            is DomainResult.Error -> UiState.Error(it.message)
            is DomainResult.Content -> UiState.Content(it.data)
        }
    }

    override suspend fun getDetailSpeciesPokemon(url: String): UiState<PokemonDetailSpecies> {
        return when (val it = repository.getDetailSpeciesPokemon(url = url)) {
            is DomainResult.Error -> UiState.Error(it.message)
            is DomainResult.Content -> UiState.Content(it.data)
        }
    }

    override suspend fun getDetailPokemonCharacteristic(id: Int): UiState<String> {
        return when (val it = repository.getDetailPokemonCharacteristic(id)) {
            is DomainResult.Error -> UiState.Error(it.message)
            is DomainResult.Content -> UiState.Content(it.data)
        }
    }

    override suspend fun getPokemonLocationAreas(id: Int): UiState<List<String>> {
        return when (val it = repository.getPokemonLocationAreas(id)) {
            is DomainResult.Error -> UiState.Error(it.message)
            is DomainResult.Content -> UiState.Content(it.data)
        }
    }

    override suspend fun getPokemonById(id: Int): UiState<PokemonDetail> {
        return when (val it = repository.getPokemonById(id)) {
            is DomainResult.Error -> UiState.Error(it.message)
            is DomainResult.Content -> UiState.Content(it.data)
        }
    }

}
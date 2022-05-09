package com.junemon.compose_stable.core.data.repository

import com.junemon.compose_stable.core.data.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.mapToDetail
import com.junemon.compose_stable.core.domain.model.mapToSpeciesDetail
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) :
    PokemonRepository {


    override suspend fun getPokemon(): DomainResult<List<PokemonDetail>> {
        return when (val result = remoteDataSource.getPokemon()) {
            is DataSourceResult.SourceValue -> {
                val data = result.data.map { singleItem ->
                    remoteDataSource.getDetailPokemon(singleItem.pokemonUrl).mapToDetail()
                }
                if (data.isNullOrEmpty()) {
                    DomainResult.Error(EMPTY_DATA)
                } else DomainResult.Content(data)
            }
            is DataSourceResult.SourceError -> {
                DomainResult.Error(result.exception.message ?: NETWORK_ERROR)
            }
        }

    }


    override suspend fun getDetailSpeciesPokemon(url: String): DomainResult<PokemonDetailSpecies> {
        return when (val result = remoteDataSource.getDetailSpeciesPokemon(url)) {
            is DataSourceResult.SourceValue -> {
                DomainResult.Content(result.data.mapToSpeciesDetail())
            }
            is DataSourceResult.SourceError -> {
                DomainResult.Error(result.exception.message ?: NETWORK_ERROR)
            }
        }
    }

    override suspend fun getDetailPokemonCharacteristic(id: Int): DomainResult<String> {
        return when (val result = remoteDataSource.getDetailPokemonCharacteristic(id)) {
            is DataSourceResult.SourceValue -> {
                DomainResult.Content(result.data)
            }
            is DataSourceResult.SourceError -> {
                DomainResult.Error(result.exception.message ?: NETWORK_ERROR)
            }
        }

    }

    override suspend fun getPokemonLocationAreas(id: Int): DomainResult<List<String>> {
        return when (val result = remoteDataSource.getPokemonLocationAreas(id)) {
            is DataSourceResult.SourceValue -> {
                DomainResult.Content(result.data)
            }
            is DataSourceResult.SourceError -> {
                DomainResult.Error(result.exception.message ?: NETWORK_ERROR)
            }

        }
    }

    override suspend fun getPokemonById(id: Int): DomainResult<PokemonDetail> {
        return when (val result = remoteDataSource.getPokemonById(id)) {
            is DataSourceResult.SourceValue -> {
                DomainResult.Content(result.data.mapToDetail())
            }
            is DataSourceResult.SourceError -> {
                DomainResult.Error(result.exception.message ?: NETWORK_ERROR)
            }

        }
    }


}


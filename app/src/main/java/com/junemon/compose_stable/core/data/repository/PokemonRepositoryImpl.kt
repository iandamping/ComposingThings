package com.junemon.compose_stable.core.data.repository

import com.junemon.compose_stable.core.data.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.data.datasource.response.PokemonAreasResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonCharacteristicResponse
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.mapToDetail
import com.junemon.compose_stable.core.domain.model.mapToSpeciesDetail
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.domain.response.PokemonDetailSpecies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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


    override fun getPokemon(): Flow<DomainResult<List<PokemonDetail>>> {
        return flow {
            when (val result = remoteDataSource.getPokemon()) {
                is DataSourceResult.SourceValue -> {
                    val data = result.data.map { singleItem ->
                        remoteDataSource.getDetailPokemon(singleItem.pokemonUrl).mapToDetail()
                    }
                    if (data.isNullOrEmpty()) {
                        emit(DomainResult.Error(EMPTY_DATA))
                    } else emit(DomainResult.Content(data))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(result.exception.message ?: NETWORK_ERROR))
                }
            }
        }
    }


    override fun getDetailSpeciesPokemon(url: String): Flow<PokemonDetailSpecies> {
        return flow {
            emit(remoteDataSource.getDetailSpeciesPokemon(url).mapToSpeciesDetail())
        }
    }

    override fun getDetailPokemonCharacteristic(id: Int): Flow<DomainResult<String>> {
        return flow {
            when (val result = remoteDataSource.getDetailPokemonCharacteristic(id)) {
                is DataSourceResult.SourceValue -> {
                    emit(DomainResult.Content(result.data))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(result.exception.message ?: NETWORK_ERROR))
                }
            }
        }
    }

    override fun getPokemonLocationAreas(id: Int): Flow<DomainResult<List<String>>> {
        return flow {
            when (val result = remoteDataSource.getPokemonLocationAreas(id)) {
                is DataSourceResult.SourceValue -> {
                    emit(DomainResult.Content(result.data))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(result.exception.message ?: NETWORK_ERROR))
                }
            }
        }
    }

    override fun getPokemonById(id: Int): Flow<DomainResult<PokemonDetail>> {
        return flow {
            when (val result = remoteDataSource.getPokemonById(id)) {
                is DataSourceResult.SourceValue -> {
                    emit(DomainResult.Content(result.data.mapToDetail()))
                }
                is DataSourceResult.SourceError -> {
                    emit(DomainResult.Error(result.exception.message ?: NETWORK_ERROR))
                }
            }
        }
    }


}


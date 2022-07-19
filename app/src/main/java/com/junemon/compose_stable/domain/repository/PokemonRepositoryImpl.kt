package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.network.NetworkConstant.EMPTY_DATA
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetailSpecies
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val dataSource: PokemonRemoteDataSource) :
    PokemonRepository {

    override suspend fun getPokemon(): Results<List<PokemonDetail>> {
        return try {
            val data = dataSource.getPokemon().map { url ->
                dataSource.getDetailPokemonByUrl(url.pokemonUrl).toDomain()
            }
            if (data.isNotEmpty()) {
                Results.Success(data)
            } else Results.Error(EMPTY_DATA)
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getDetailSpeciesPokemon(url: String): Results<PokemonDetailSpecies> {
        return try {
            Results.Success(dataSource.getDetailSpeciesPokemon(url).toDomain())
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getDetailPokemonCharacteristic(id: Int): Results<String> {
        return try {
            Results.Success(dataSource.getDetailPokemonCharacteristic(id))
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getPokemonLocationAreas(id: Int): Results<List<String>> {
        return try {
            Results.Success(dataSource.getPokemonLocationAreas(id))
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getPokemonById(id: Int): Results<PokemonDetail> {
        return try {
            Results.Success(dataSource.getDetailPokemonById(id).toDomain())
        } catch (e: Exception) {
            Results.Error(e.message ?: NetworkConstant.NETWORK_ERROR)
        }
    }
}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.network.PokemonApi
import com.junemon.compose_stable.datasource.response.pokemon.PokemonDetailResponse
import com.junemon.compose_stable.datasource.response.pokemon.PokemonResultsResponse
import com.junemon.compose_stable.datasource.response.pokemon.PokemonSpeciesDetailResponse
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(private val api: PokemonApi) : PokemonRemoteDataSource {

    override suspend fun getPokemon(): List<PokemonResultsResponse> {
        try {
            val response = api.getMainPokemon()
            return response.pokemonResults
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getDetailPokemonByUrl(url: String): PokemonDetailResponse {
        return try {
            api.getPokemonDetailByUrl(url)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getDetailPokemonCharacteristic(id: Int): String {
        return try {
            api.getPokemonCharacteristic(id).descriptions[0].description
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPokemonLocationAreas(id: Int): List<String> {
        return try {
            api.getPokemonLocationAreas(id).map { it.area.name }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getDetailPokemonById(id: Int): PokemonDetailResponse {
        return try {
            api.getPokemonById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse {
        return try {
            api.getPokemonSpecies(url)
        } catch (e: Exception) {
            throw e
        }
    }
}
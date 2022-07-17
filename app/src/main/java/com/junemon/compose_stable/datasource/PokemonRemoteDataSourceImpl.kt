package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.network.NetworkConstant
import com.junemon.compose_stable.network.PokemonApi
import com.junemon.compose_stable.response.pokemon.PokemonDetailResponse
import com.junemon.compose_stable.response.pokemon.PokemonResultsResponse
import com.junemon.compose_stable.response.pokemon.PokemonSpeciesDetailResponse

class PokemonRemoteDataSourceImpl(private val api: PokemonApi) : PokemonRemoteDataSource {

    override suspend fun getPokemon(): List<PokemonResultsResponse> {
        try {
            val response = api.getMainPokemon()
            return response.pokemonResults
        } catch (e: Exception) {
            throw Exception(NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getDetailPokemonByUrl(url: String): PokemonDetailResponse {
        try {
            return api.getPokemonDetailByUrl(url)
        } catch (e: Exception) {
            throw Exception(NetworkConstant.NETWORK_ERROR)
        }
    }

    override suspend fun getDetailPokemonCharacteristic(id: Int): String {
       return api.getPokemonCharacteristic(id).descriptions[7].description
    }

    override suspend fun getPokemonLocationAreas(id: Int): List<String> {
        return api.getPokemonLocationAreas(id).map { it.area.name }
    }

    override suspend fun getDetailPokemonById(id: Int): PokemonDetailResponse {
        return api.getPokemonById(id)
    }

    override suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse {
        return api.getPokemonSpecies(url)
    }
}
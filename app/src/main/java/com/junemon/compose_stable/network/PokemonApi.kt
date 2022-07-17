package com.junemon.compose_stable.network

import com.junemon.compose_stable.network.NetworkConstant.GET_POKEMON
import com.junemon.compose_stable.network.NetworkConstant.GET_POKEMON_AREAS
import com.junemon.compose_stable.network.NetworkConstant.GET_POKEMON_CHARACTERISTIC
import com.junemon.compose_stable.response.pokemon.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonApi {

    @GET(GET_POKEMON)
    suspend fun getMainPokemon(): PokemonMainResponse

    @GET
    suspend fun getPokemonDetailByUrl(@Url url: String): PokemonDetailResponse

    @GET("$GET_POKEMON_CHARACTERISTIC/{id}")
    suspend fun getPokemonCharacteristic(@Path("id") id: Int): PokemonCharacteristicResponse

    @GET("$GET_POKEMON/{id}/$GET_POKEMON_AREAS")
    suspend fun getPokemonLocationAreas(@Path("id") id: Int): List<PokemonAreasResponse>

    @GET("$GET_POKEMON/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonDetailResponse

    @GET
    suspend fun getPokemonSpecies(@Url url: String): PokemonSpeciesDetailResponse
}
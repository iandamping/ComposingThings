package com.junemon.compose_stable.core.data.datasource.remote

import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.GET_POKEMON
import com.junemon.compose_stable.core.data.datasource.response.PokemonMainResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonDetailResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonSpeciesDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {

    @GET(GET_POKEMON)
    suspend fun getMainPokemon(): Response<PokemonMainResponse>

    @GET
    suspend fun getPokemon(@Url url: String): PokemonDetailResponse

    @GET
    suspend fun getPokemonSpecies(@Url url: String): PokemonSpeciesDetailResponse
}
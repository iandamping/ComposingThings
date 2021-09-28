package com.junemon.compose_stable.core.data.datasource

import com.junemon.compose_stable.core.data.datasource.remote.ApiInterface
import com.junemon.compose_stable.core.data.datasource.remote.BaseSource
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.EMPTY_DATA
import com.junemon.compose_stable.core.data.datasource.response.PokemonDetailResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonResultsResponse
import com.junemon.compose_stable.core.data.datasource.response.PokemonSpeciesDetailResponse
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.data.model.ApiResult
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonRemoteDataSourceImpl @Inject constructor(
    baseSource: BaseSource,
    private val api: ApiInterface
) : PokemonRemoteDataSource, BaseSource by baseSource {
    override suspend fun getPokemon(): DataSourceResult<List<PokemonResultsResponse>> {
        return when (val response = oneShotCalls { api.getMainPokemon() }) {
            is ApiResult.Error -> DataSourceResult.SourceError(response.exception)
            is ApiResult.Success -> if (response.data.pokemonResults.isNullOrEmpty()) {
                DataSourceResult.SourceError(Exception(EMPTY_DATA))
            } else DataSourceResult.SourceValue(response.data.pokemonResults)
        }
    }

    override suspend fun getDetailPokemon(url: String): PokemonDetailResponse {
        return api.getPokemon(url)
    }

    override suspend fun getDetailSpeciesPokemon(url: String): PokemonSpeciesDetailResponse {
        return api.getPokemonSpecies(url)
    }
}

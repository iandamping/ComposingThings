package com.junemon.compose_stable.core.data.datasource.cache

import kotlinx.coroutines.flow.Flow

interface PokemonCacheDataSource {

    fun loadPokemon(): Flow<List<PokemonEntity>>

    suspend fun insertPokemon(vararg data: PokemonEntity)

    suspend fun deletePokemonById(selectedId: Int)
}
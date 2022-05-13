package com.junemon.compose_stable.core.data.datasource.cache

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonCacheDataSourceImpl @Inject constructor(private val dao:PokemonDao):PokemonCacheDataSource {

    override fun loadPokemon(): Flow<List<PokemonEntity>> {
        return dao.loadPokemon()
    }

    override suspend fun insertPokemon(vararg data: PokemonEntity) {
       dao.insertPokemon(*data)
    }

    override suspend fun deletePokemonById(selectedId: Int) {
       dao.deletePokemonById(selectedId)
    }

}
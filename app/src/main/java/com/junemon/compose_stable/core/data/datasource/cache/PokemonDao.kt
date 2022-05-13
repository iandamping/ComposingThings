package com.junemon.compose_stable.core.data.datasource.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM cache_pokemon")
    fun loadPokemon(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg data: PokemonEntity)

    @Query("DELETE FROM cache_pokemon where pokemon_primary_id = :selectedId")
    suspend fun deletePokemonById(selectedId: Int)

    @Query("DELETE FROM cache_pokemon")
    suspend fun deleteAllPokemon()
}
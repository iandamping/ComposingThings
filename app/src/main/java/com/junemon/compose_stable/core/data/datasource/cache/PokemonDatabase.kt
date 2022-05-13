package com.junemon.compose_stable.core.data.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
package com.junemon.compose_stable.core.di.module

import android.content.Context
import androidx.room.Room
import com.junemon.compose_stable.core.data.datasource.cache.PokemonDao
import com.junemon.compose_stable.core.data.datasource.cache.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, "pokeDb")
            .fallbackToDestructiveMigration().build()


    @Provides
    fun provideFavDao(db: PokemonDatabase): PokemonDao {
        return db.pokemonDao()
    }


}
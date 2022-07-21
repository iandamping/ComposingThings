package com.junemon.compose_stable.di

import com.junemon.compose_stable.core.datasource.*
import com.junemon.compose_stable.core.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    fun bindsMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    fun bindsPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository
}
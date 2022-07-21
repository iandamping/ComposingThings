package com.junemon.compose_stable.di

import com.junemon.compose_stable.core.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {

    @Binds
    @Singleton
    fun bindsNewsRemoteDataSource(dataSource: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    @Singleton
    fun bindsMovieRemoteDataSource(dataSource: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @Singleton
    fun bindsPokemonRemoteDataSource(dataSource: PokemonRemoteDataSourceImpl): PokemonRemoteDataSource
}
package com.junemon.compose_stable.core.di.module

import com.junemon.compose_stable.core.data.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.core.data.datasource.PokemonRemoteDataSourceImpl
import com.junemon.compose_stable.core.data.datasource.cache.PokemonCacheDataSource
import com.junemon.compose_stable.core.data.datasource.cache.PokemonCacheDataSourceImpl
import com.junemon.compose_stable.core.data.datasource.remote.BaseSource
import com.junemon.compose_stable.core.data.datasource.remote.BaseSourceImpl
import com.junemon.compose_stable.core.data.repository.PokemonRepositoryImpl
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCase
import com.junemon.compose_stable.core.domain.usecase.PokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsBaseSource(baseSource: BaseSourceImpl): BaseSource

    @Binds
    @Singleton
    fun bindsPokemonRemoteSource(dataSource: PokemonRemoteDataSourceImpl): PokemonRemoteDataSource

    @Binds
    @Singleton
    fun bindsPokemonCacheDataSource(dataSource: PokemonCacheDataSourceImpl): PokemonCacheDataSource

    @Binds
    @Singleton
    fun bindsPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository

    @Binds
    @Singleton
    fun bindsPokemonUseCase(useCase: PokemonUseCaseImpl): PokemonUseCase
}
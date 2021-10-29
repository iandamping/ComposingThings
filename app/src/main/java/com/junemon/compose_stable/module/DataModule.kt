package com.junemon.compose_stable.module

import com.junemon.compose_stable.core.RemoteDataSource
import com.junemon.compose_stable.core.RemoteDataSourceImpl
import com.junemon.compose_stable.core.Repository
import com.junemon.compose_stable.core.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindDataSource(dataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}
package com.junemon.compose_stable.module

import com.junemon.compose_stable.core.data.datasource.NewsRemoteDataSource
import com.junemon.compose_stable.core.data.datasource.NewsRemoteDataSourceImpl
import com.junemon.compose_stable.core.data.datasource.remote.BaseSource
import com.junemon.compose_stable.core.data.datasource.remote.BaseSourceImpl
import com.junemon.compose_stable.core.data.repository.NewsRepositoryImpl
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import com.junemon.compose_stable.core.domain.usecase.NewsUseCaseImpl
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
    fun bindsBaseSource(baseSource: BaseSourceImpl):BaseSource

    @Binds
    @Singleton
    fun bindsNewsRemoteSource(dataSource: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    @Singleton
    fun bindsNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    fun bindsNewsUseCase(useCase: NewsUseCaseImpl): NewsUseCase
}
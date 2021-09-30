package com.junemon.compose_stable.core.data.repository

import com.junemon.compose_stable.core.data.datasource.NewsRemoteDataSource
import com.junemon.compose_stable.core.data.datasource.remote.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.data.model.DataSourceResult
import com.junemon.compose_stable.core.data.model.response.NewsResponse
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.mapToDomain
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override fun getNews(): Flow<DomainResult<List<News>>> {
        return flow {
            when (val result = remoteDataSource.getNews()) {
                is DataSourceResult.SourceError -> emit(
                    DomainResult.Error(
                        result.exception.message ?: NETWORK_ERROR
                    )
                )
                is DataSourceResult.SourceValue -> emit(DomainResult.Data(result.data.mapToDomain()))
            }
        }
    }

    override fun searchNews(query: String): Flow<DomainResult<List<News>>> {
        return flow {
            when (val result = remoteDataSource.searchNews(query)) {
                is DataSourceResult.SourceError -> emit(
                    DomainResult.Error(
                        result.exception.message ?: NETWORK_ERROR
                    )
                )
                is DataSourceResult.SourceValue -> emit(DomainResult.Data(result.data.mapToDomain()))
            }
        }
    }
}
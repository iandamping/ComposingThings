package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import com.junemon.compose_stable.core.presentation.model.UiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class NewsUseCaseImpl @Inject constructor(private val repository: NewsRepository) : NewsUseCase {

    override fun getNews(): Flow<UiResult<List<News>>> {
        return repository.getNews().map {
            when (it) {
                is DomainResult.Data -> UiResult.Data(it.data)

                is DomainResult.Error -> UiResult.Error(it.message)
            }
        }

    }

    override fun searchNews(query: String): Flow<UiResult<List<News>>> {
        return repository.searchNews(query = query).map {
            when (it) {
                is DomainResult.Data -> UiResult.Data(it.data)

                is DomainResult.Error -> UiResult.Error(it.message)
            }
        }
    }
}
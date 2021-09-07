package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class NewsUseCaseImpl @Inject constructor(private val repository: NewsRepository) : NewsUseCase {

    override fun getNews(): Flow<DomainResult<List<News>>> {
        return repository.getNews()
    }
}
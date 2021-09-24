package com.junemon.compose_stable.core.domain.usecase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class NewsUseCaseImpl @Inject constructor(private val repository: NewsRepository) : NewsUseCase {

    @Composable
    override fun getNews(): State<DomainResult<List<News>>> {
        return repository.getNews().collectAsState(initial = DomainResult.Loading)
    }
}
package com.junemon.compose_stable.core.domain.usecase

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
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

    @Composable
    override fun getNews(): State<DomainResult<List<News>>> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val homeNewsFlowLifecycleAware = remember(repository.getNews(), lifecycleOwner) {
            repository.getNews().flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }
        return homeNewsFlowLifecycleAware.collectAsState(initial = DomainResult.Loading)
    }

    override fun searchNews(query: String): Flow<DomainResult<List<News>>> {
        return repository.searchNews(query = query)
    }
}
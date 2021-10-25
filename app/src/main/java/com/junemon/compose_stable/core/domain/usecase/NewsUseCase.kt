package com.junemon.compose_stable.core.domain.usecase

import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.presentation.model.UiResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface NewsUseCase {

    fun getNews(): Flow<UiResult<List<News>>>

    fun searchNews(query: String): Flow<UiResult<List<News>>>
}
package com.junemon.compose_stable.core.domain.usecase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface NewsUseCase {

    @Composable
    fun getNews(): State<DomainResult<List<News>>>
}
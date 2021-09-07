package com.junemon.compose_stable.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: NewsUseCase
) : ViewModel() {

    fun getNews(): LiveData<DomainResult<List<News>>> =
        useCase.getNews().asLiveData(viewModelScope.coroutineContext)
}
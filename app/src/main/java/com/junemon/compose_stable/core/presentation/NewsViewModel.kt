package com.junemon.compose_stable.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import com.junemon.compose_stable.core.presentation.state.news.NewsSearchState
import com.junemon.compose_stable.core.presentation.state.news.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private var fetchJob: Job? = null

    private val _userQuery: MutableStateFlow<String> = MutableStateFlow("")
    val userQuery: StateFlow<String> = _userQuery.asStateFlow()

    var uiStateOfSearchNews by mutableStateOf(NewsSearchState.initial())
        private set

    var uiStateOfNews by mutableStateOf(NewsState.initial())
        private set


    init {
        viewModelScope.launch {
            uiStateOfNews = when (val results = repository.getNews()) {
                is Results.Error -> uiStateOfNews.copy(
                    isLoading = false,
                    failedMessage = results.errorMessage
                )
                is Results.Success -> uiStateOfNews.copy(isLoading = false, data = results.data)
            }
        }

        viewModelScope.launch {
            userQuery.debounce(300)
                .distinctUntilChanged()
                .filter {
                    it.trim().isNotEmpty()
                }.collect { userSearch ->
                    searchNews(userSearch)
                }
        }
    }

    fun setSearch(data: String) {
        _userQuery.value = data
    }

    fun searchNews(data: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            uiStateOfSearchNews =
                when (val results = repository.searchNews(data)) {
                    is Results.Error -> uiStateOfSearchNews.copy(
                        isLoading = false,
                        failedMessage = results.errorMessage
                    )
                    is Results.Success -> uiStateOfSearchNews.copy(
                        isLoading = false,
                        data = results.data
                    )
                }
        }
    }


}
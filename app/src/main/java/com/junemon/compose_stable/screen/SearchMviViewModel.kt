package com.junemon.compose_stable.screen

import com.junemon.compose_stable.base.BaseViewModel
import com.junemon.compose_stable.base.Reducer
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import com.junemon.compose_stable.core.presentation.model.SearchScreenState
import com.junemon.compose_stable.core.presentation.model.SearchScreenUiEvent
import com.junemon.compose_stable.core.presentation.model.UiResult
import com.junemon.compose_stable.util.search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 24,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class SearchMviViewModel @Inject constructor(
    private val domainUseCase: NewsUseCase
) : BaseViewModel<SearchScreenState>() {

    private val reducer = SearchReducer(SearchScreenState.initial())

    private val _userQuery: MutableStateFlow<String> = MutableStateFlow("")
    val userQuery: StateFlow<String> = _userQuery.asStateFlow()

    fun setSearchState(data: String) {
        _userQuery.value = data
    }

    override val state: Flow<SearchScreenState>
        get() = reducer.state

    private fun sendEvent(event: SearchScreenUiEvent) {
        reducer.sendEvent(event)
    }

    fun setToIdle() {
        sendEvent(SearchScreenUiEvent.Idle)
    }


    init {
        consumeSuspend {
            userQuery.search { query ->
                domainUseCase.searchNews(query)
            }.collect {
                when (val data = it) {
                    is UiResult.Data -> sendEvent(SearchScreenUiEvent.ShowData(data.data))
                    is UiResult.Error -> sendEvent(SearchScreenUiEvent.FailedMessage(data.message))
                }
            }
        }
    }

    private inner class SearchReducer(initial: SearchScreenState) :
        Reducer<SearchScreenState, SearchScreenUiEvent>(initial) {
        override fun reduce(oldState: SearchScreenState, event: SearchScreenUiEvent) {
            when (event) {
                is SearchScreenUiEvent.ShowData ->{
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is SearchScreenUiEvent.FailedMessage ->
                    setState(oldState.copy(isLoading = false, failedMessage = event.message))
                is SearchScreenUiEvent.Idle -> setState(
                    oldState.copy(
                        isLoading = true,
                        failedMessage = "",
                        data = emptyList()
                    )
                )
            }
        }
    }
}
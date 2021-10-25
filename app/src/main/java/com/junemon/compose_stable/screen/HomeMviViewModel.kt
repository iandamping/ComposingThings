package com.junemon.compose_stable.screen

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.base.BaseViewModel
import com.junemon.compose_stable.base.Reducer
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import com.junemon.compose_stable.core.presentation.model.HomeScreenState
import com.junemon.compose_stable.core.presentation.model.HomeScreenUiEvent
import com.junemon.compose_stable.core.presentation.model.SearchScreenUiEvent
import com.junemon.compose_stable.core.presentation.model.UiResult
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.navigation.ScreensNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ian Damping on 24,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class HomeMviViewModel @Inject constructor(
    private val domainUseCase: NewsUseCase
):BaseViewModel<HomeScreenState>() {


    private val reducer = MainReducer(HomeScreenState.initial())

    override val state: Flow<HomeScreenState>
        get() = reducer.state

    init{
        consumeSuspend {
            domainUseCase.getNews().collect {
                when(val data = it){
                    is UiResult.Data -> sendEvent(HomeScreenUiEvent.ShowData(data.data))
                    is UiResult.Error -> sendEvent(HomeScreenUiEvent.FailedMessage(data.message))
                }
            }
        }
    }

    private fun sendEvent(event: HomeScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private inner class MainReducer(initial: HomeScreenState) :
        Reducer<HomeScreenState, HomeScreenUiEvent>(initial) {
        override fun reduce(oldState: HomeScreenState, event: HomeScreenUiEvent) {
            when (event) {
                is HomeScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is HomeScreenUiEvent.FailedMessage ->
                    setState(oldState.copy(isLoading = false, failedMessage = event.message))
            }
        }
    }



}
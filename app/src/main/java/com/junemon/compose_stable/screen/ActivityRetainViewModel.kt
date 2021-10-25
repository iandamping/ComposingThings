package com.junemon.compose_stable.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ian Damping on 24,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

class ActivityRetainViewModel:ViewModel() {

    private var _newsDetailState: Channel<String?> = Channel(Channel.CONFLATED)
    val newsDetailFlow: Flow<String?> =
        _newsDetailState.receiveAsFlow().distinctUntilChanged()

    fun switchScreenNewsDetail(data: String?) {
        viewModelScope.launch {
            _newsDetailState.send(data)
        }
    }

}
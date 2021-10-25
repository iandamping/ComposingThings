package com.junemon.compose_stable.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : MviUiState> : ViewModel() {

    private var baseJob: Job? = null

    abstract val state: Flow<T>

    protected fun consumeSuspend(func: suspend () -> Unit) {
        baseJob?.cancel()
        baseJob = viewModelScope.launch {
            func.invoke()
        }
    }

    override fun onCleared() {
        super.onCleared()
        baseJob?.cancel()
    }

}
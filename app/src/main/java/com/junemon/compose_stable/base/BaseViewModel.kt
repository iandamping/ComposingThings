package com.junemon.compose_stable.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<T : MviUiState> : ViewModel() {

    abstract val state: Flow<T>

}
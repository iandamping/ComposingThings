package com.junemon.compose_stable.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> StateFlow<String>.search(searchData: (String) -> Flow<T>) = this.debounce(300)
    .distinctUntilChanged()
    .filter {
        it.trim().isNotEmpty()
    }.flatMapLatest {
        searchData.invoke(it)
    }

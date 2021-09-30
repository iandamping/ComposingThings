package com.junemon.compose_stable.util

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.asLiveData
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Composable
fun ComposingWithTheme(content: @Composable () -> Unit) {
    ComposingThingsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content.invoke()
        }
    }
}

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> StateFlow<String>.search(searchData: (String) -> Flow<T>) = this.debounce(300)
    .distinctUntilChanged()
    .filter {
        it.trim().isNotEmpty()
    }.flatMapLatest {
        searchData.invoke(it)
    }


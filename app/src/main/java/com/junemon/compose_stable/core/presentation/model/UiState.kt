package com.junemon.compose_stable.core.presentation.model

sealed class UiState<out T> {
    data class Content<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
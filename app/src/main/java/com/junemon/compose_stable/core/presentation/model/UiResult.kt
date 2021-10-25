package com.junemon.compose_stable.core.presentation.model


/**
 * Created by Ian Damping on 24,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class UiResult<out T> {
    data class Data<out T>(val data: T) : UiResult<T>()
    data class Error(val message: String) : UiResult<Nothing>()
}
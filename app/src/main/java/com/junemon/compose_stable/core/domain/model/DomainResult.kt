package com.junemon.compose_stable.core.domain.model

sealed class DomainResult<out T> {
    data class Data<out T>(val data: T) : DomainResult<T>()
    data class Error(val message: String) : DomainResult<Nothing>()
}
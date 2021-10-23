package com.junemon.compose_stable.core.domain.model


sealed class DomainResult<out T> {
    data class Content<out T>(val data: T) : DomainResult<T>()
    data class Error(val message: String) : DomainResult<Nothing>()
}
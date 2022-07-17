package com.junemon.compose_stable

sealed class Results<out R> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val errorMessage:String) : Results<Nothing>()
}
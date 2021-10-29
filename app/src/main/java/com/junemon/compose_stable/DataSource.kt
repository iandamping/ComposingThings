package com.junemon.compose_stable

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class DataSource<out T>{
    data class Result<out T>(val data:T):DataSource<T>()
    object Fail : DataSource<Nothing>()
}

sealed class DomainSource<out T>{
    data class Result<out T>(val data:T):DomainSource<T>()
    data class Fail(val message:String?) : DomainSource<Nothing>()
}

sealed class PushFirebaseStatus<out R> {
    object Success : PushFirebaseStatus<Nothing>()
    data class Error(val exception: Exception) : PushFirebaseStatus<Nothing>()
}

sealed class PushFirebase {
    data class Changed(val snapshot: DataSnapshot) : PushFirebase()
    data class Cancelled(val error: DatabaseError) : PushFirebase()
}


package com.junemon.compose_stable.util

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.junemon.compose_stable.PushFirebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun DatabaseReference.valueEventFlow(): Flow<PushFirebase> = callbackFlow {
    val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot): Unit =
            trySendBlocking(PushFirebase.Changed(snapshot)).getOrThrow()
//            sendBlocking(PushFirebase.Changed(snapshot))

        override fun onCancelled(error: DatabaseError): Unit =
            trySendBlocking(PushFirebase.Cancelled(error)).getOrThrow()
//            sendBlocking(PushFirebase.Cancelled(error))
    }
    addValueEventListener(valueEventListener)
    awaitClose {
        removeEventListener(valueEventListener)
    }
}

suspend fun DatabaseReference.singleValueEvent(): PushFirebase =
    suspendCancellableCoroutine { continuation ->

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                continuation.resume(PushFirebase.Cancelled(error))
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                continuation.resume(PushFirebase.Changed(snapshot))
            }
        }
        addListenerForSingleValueEvent(valueEventListener) // Subscribe to the event
    }

fun Int?.nullableDp(): TextUnit {
    return this?.sp ?: 16.sp
}

package com.junemon.compose_stable.core

import com.google.firebase.database.DatabaseReference
import com.junemon.compose_stable.DataSource
import com.junemon.compose_stable.DynamicContent
import com.junemon.compose_stable.PushFirebase
import com.junemon.compose_stable.PushFirebaseStatus
import com.junemon.compose_stable.util.Constant.ERROR_PUSH_FIREBASE
import com.junemon.compose_stable.util.valueEventFlow
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class RemoteDataSourceImpl @Inject constructor(private val databaseReference: DatabaseReference) :
    RemoteDataSource {

    override fun getDynamicContent(): Flow<DataSource<List<DynamicContent>>> {
        return databaseReference.valueEventFlow().map { value ->
            when (value) {
                is PushFirebase.Cancelled -> DataSource.Fail
                is PushFirebase.Changed -> {
                    val result = value.snapshot.children.mapNotNull {
                        it.getValue(DynamicContent::class.java)
                    }.toList()

                    DataSource.Result(result)
                }
            }
        }
    }

    override suspend fun pushDynamicContent(data: List<DynamicContent>): PushFirebaseStatus<Nothing> {
        val result: CompletableDeferred<PushFirebaseStatus<Nothing>> = CompletableDeferred()
        if (data.isNotEmpty()) {
            databaseReference.setValue(data)
                .addOnFailureListener {
                    result.complete(PushFirebaseStatus.Error(it))
                }.addOnSuccessListener {
                    result.complete(PushFirebaseStatus.Success)
                }
        } else {
            result.complete(PushFirebaseStatus.Error(Exception(ERROR_PUSH_FIREBASE)))
        }
        return result.await()
    }
}
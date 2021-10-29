package com.junemon.compose_stable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.Repository
import com.junemon.compose_stable.util.Constant.listOfDynamicContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class DynamicViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.pushDynamicContent(data = listOfDynamicContent).collect { value ->
                when (value) {
                    is PushFirebaseStatus.Error -> Timber.e("failed : ${value.exception}")
                    PushFirebaseStatus.Success -> Timber.e("success")
                }
            }
        }

    }

    fun getDynamicContent(): Flow<DomainSource<List<DynamicContent>>> =
        repository.getDynamicContent()
}
package com.junemon.compose_stable.screen

import com.junemon.compose_stable.core.data.datasource.cache.PokemonEntity
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import javax.annotation.concurrent.Immutable


/**
 * Created by Ian Damping on 21,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Immutable
data class HomeScreenCachedState(
    val isLoading: Boolean,
    val failedMessage: String,
    val data: List<PokemonEntity>,
) {

    companion object {
        fun initial() = HomeScreenCachedState(
            isLoading = true,
            failedMessage = "",
            data = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}
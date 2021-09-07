package com.junemon.compose_stable.core.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class BaseResponse<out T>(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("articles") val articles: List<T>
)

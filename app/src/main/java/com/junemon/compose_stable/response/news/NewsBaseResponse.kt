package com.junemon.compose_stable.response.news

import com.google.gson.annotations.SerializedName

data class NewsBaseResponse<out T>(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("articles") val articles: List<T>
)
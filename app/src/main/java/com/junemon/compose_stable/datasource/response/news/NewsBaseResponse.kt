package com.junemon.compose_stable.datasource.response.news

import com.google.gson.annotations.SerializedName

data class NewsBaseResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("articles") val articles: List<NewsResponse>
)
package com.junemon.compose_stable.response.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("source") val source: SourceResponse,
    @field:SerializedName("author") val newsAuthor: String?,
    @field:SerializedName("title") val newsTitle: String?,
    @field:SerializedName("description") val newsDescription: String?,
    @field:SerializedName("urlToImage") val newsImage: String?,
    @field:SerializedName("publishedAt") val publishedAt: String?,
    @field:SerializedName("content") val newsContent: String?
)

data class SourceResponse(
    @field:SerializedName("id") val sourceId: String?,
    @field:SerializedName("name") val sourceName: String?,
)

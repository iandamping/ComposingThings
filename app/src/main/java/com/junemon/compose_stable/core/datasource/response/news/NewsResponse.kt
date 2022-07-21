package com.junemon.compose_stable.core.datasource.response.news

import com.google.gson.annotations.SerializedName
import com.junemon.compose_stable.core.domain.model.Dto
import com.junemon.compose_stable.core.domain.model.news.News

data class NewsResponse(
    @field:SerializedName("source") val source: SourceResponse,
    @field:SerializedName("author") val newsAuthor: String?,
    @field:SerializedName("title") val newsTitle: String?,
    @field:SerializedName("description") val newsDescription: String?,
    @field:SerializedName("urlToImage") val newsImage: String?,
    @field:SerializedName("publishedAt") val publishedAt: String?,
    @field:SerializedName("content") val newsContent: String?
) : Dto {

    override fun toDomain(): News {
        return News(
            sourceName = source.sourceName ?: "Source not available",
            newsAuthor = newsAuthor ?: "Author Name not available",
            newsTitle = newsTitle ?: "News Tittle not available",
            newsDescription = newsDescription ?: "News Description not available",
            newsImage = newsImage ?: "News Image not available",
            publishedAt = publishedAt ?: "Published Time not available",
            newsContent = newsContent ?: "Content not available"
        )
    }
}

data class SourceResponse(
    @field:SerializedName("id") val sourceId: String?,
    @field:SerializedName("name") val sourceName: String?,
)

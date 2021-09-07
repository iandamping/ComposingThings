package com.junemon.compose_stable.core.domain.model

import com.junemon.compose_stable.core.data.model.response.NewsResponse
import com.junemon.compose_stable.core.domain.model.response.News

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

fun NewsResponse.mapToDomain(): News = News(
    sourceName = source.sourceName ?: "Source not available",
    newsAuthor = newsAuthor ?: "Author Name not available",
    newsTitle = newsTitle ?: "News Tittle not available",
    newsDescription = newsDescription ?: "News Description not available",
    newsImage = newsImage ?: "News Image not available",
    publishedAt = publishedAt ?: "Published Time not available",
    newsContent = newsContent ?: "Content not available"
)

fun List<NewsResponse>.mapToDomain():List<News> = map{ it.mapToDomain()}

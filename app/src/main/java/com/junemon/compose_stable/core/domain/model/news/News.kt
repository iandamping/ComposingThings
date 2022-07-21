package com.junemon.compose_stable.core.domain.model.news

import com.junemon.compose_stable.core.domain.model.Domain

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class News(
    val sourceName: String,
    val newsAuthor: String,
    val newsTitle: String,
    val newsDescription: String,
    val newsImage: String,
    val publishedAt: String,
    val newsContent: String
) : Domain
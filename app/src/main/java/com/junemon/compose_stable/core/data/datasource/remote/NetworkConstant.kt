package com.junemon.compose_stable.core.data.datasource.remote

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
object NetworkConstant {
    const val cacheSize = 10L * 1024 * 1024 // 10MB
    const val BASE_URL = "https://newsapi.org/v2/"
    const val GET_ALL_NEWS = "everything"
    const val GET_TOP_HEADLINES = "top-headlines"
    const val GET_NEWS_DETAIL = "news/get-detail"
    const val NETWORK_ERROR = "Network Error"
    const val API_KEY = "25bd4439e7564164a9ab567975428415"
}
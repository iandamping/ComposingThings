package com.junemon.compose_stable

import com.junemon.compose_stable.response.news.NewsBaseResponse
import com.junemon.compose_stable.response.news.NewsResponse
import com.junemon.compose_stable.response.news.SourceResponse

object DummyNews {

    val DUMMY_NEWS_SOURCE = SourceResponse("a","a")
    val DUMMY_NEWS_RESPONSE_1 = NewsResponse(DUMMY_NEWS_SOURCE, "a","a","a","a","a","a")
    val DUMMY_NEWS_RESPONSE_2 = NewsResponse(DUMMY_NEWS_SOURCE, "a","a","a","a","a","a")
    val DUMMY_NEWS_RESPONSE_3 = NewsResponse(DUMMY_NEWS_SOURCE, "a","a","a","a","a","a")
    val DUMMY_LIST_NEWS_RESPONSE = listOf(DUMMY_NEWS_RESPONSE_1, DUMMY_NEWS_RESPONSE_2,
        DUMMY_NEWS_RESPONSE_3)
    val DUMMY_NEWS_BASE_RESPONSE = NewsBaseResponse("a",DUMMY_LIST_NEWS_RESPONSE)
}
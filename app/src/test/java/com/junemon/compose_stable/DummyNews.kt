package com.junemon.compose_stable

import com.junemon.compose_stable.core.datasource.response.news.NewsBaseResponse
import com.junemon.compose_stable.core.datasource.response.news.NewsResponse
import com.junemon.compose_stable.core.datasource.response.news.SourceResponse
import com.junemon.compose_stable.core.domain.model.news.News

object DummyNews {

    val DUMMY_NEWS_SOURCE = SourceResponse("a", "a")
    val DUMMY_NEWS_RESPONSE_1 = NewsResponse(DUMMY_NEWS_SOURCE, "a", "a", "a", "a", "a", "a")
    val DUMMY_NEWS_RESPONSE_2 = NewsResponse(DUMMY_NEWS_SOURCE, "a", "a", "a", "a", "a", "a")
    val DUMMY_NEWS_RESPONSE_3 = NewsResponse(DUMMY_NEWS_SOURCE, "a", "a", "a", "a", "a", "a")
    val DUMMY_LIST_NEWS_RESPONSE = listOf(
        DUMMY_NEWS_RESPONSE_1, DUMMY_NEWS_RESPONSE_2,
        DUMMY_NEWS_RESPONSE_3
    )
    val DUMMY_NEWS_BASE_RESPONSE = NewsBaseResponse("a", DUMMY_LIST_NEWS_RESPONSE)


    val DUMMY_REPO_NEWS_1 = News("a", "a", "a", "a", "a", "a", "a")
    val DUMMY_REPO_NEWS_2 = News("a", "a", "a", "a", "a", "a", "a")
    val DUMMY_REPO_NEWS_3 = News("a", "a", "a", "a", "a", "a", "a")
    val DUMMY_LIST_REPO_NEWS = listOf(DUMMY_REPO_NEWS_1, DUMMY_REPO_NEWS_2, DUMMY_REPO_NEWS_3)
}
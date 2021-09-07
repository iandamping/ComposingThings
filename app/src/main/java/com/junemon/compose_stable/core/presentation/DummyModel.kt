package com.junemon.compose_stable.core.presentation

import com.junemon.compose_stable.core.domain.model.response.News

/**
 * Created by Ian Damping on 07,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
object DummyModel {
    val DUMMY_NEWS_1 = News("a","a","a","a","a","a","a")
    val DUMMY_NEWS_2 = News("b","b","b","b","b","b","b")
    val LIST_DUMMY_NEWS = listOf<News>(DUMMY_NEWS_1, DUMMY_NEWS_2)
}
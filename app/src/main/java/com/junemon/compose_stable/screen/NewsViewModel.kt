package com.junemon.compose_stable.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import com.junemon.compose_stable.core.presentation.common.LoadingUseCase
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val domainUseCase: NewsUseCase,
    private val screensUseCase: ScreensUseCase,
    private val loadingUseCase: LoadingUseCase
) : ViewModel() {

    fun getNews(): LiveData<DomainResult<List<News>>> =
        domainUseCase.getNews().asLiveData(viewModelScope.coroutineContext)

    @ExperimentalUnitApi
    @Composable
    fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier) =
        screensUseCase.ListNews(
            news = news,
            newsSelect = newsSelect,
            modifier = modifier
        )

    @ExperimentalUnitApi
    @Composable
    fun NewsDetail(news: News, modifier: Modifier) =
        screensUseCase.NewsDetail(
            news = news,
            modifier = modifier
        )

    @Composable
    fun LottieCirclingLoading() = loadingUseCase.LottieCirclingLoading()

    @Composable
    fun LottieFluidLoading() = loadingUseCase.LottieFluidLoading()

    @Composable
    fun Shimmer(itemSize: Int, modifier: Modifier) = loadingUseCase.Shimmer(
        itemSize = itemSize,
        modifier = modifier
    )
}
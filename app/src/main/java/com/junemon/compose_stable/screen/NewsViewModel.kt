package com.junemon.compose_stable.screen

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.domain.usecase.NewsUseCase
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.navigation.ScreensNavigation
import com.junemon.compose_stable.util.search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ian Damping on 06,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val domainUseCase: NewsUseCase,
    private val screensUseCase: ScreensUseCase
) : ViewModel() {

    private val _searchState: MutableStateFlow<String> = MutableStateFlow("")
    val searchState: StateFlow<String> = _searchState.asStateFlow()

    fun setSearchState(data: String) {
        _searchState.value = data
    }

    @Composable
    fun getSearchState() = searchState.search { query ->
        domainUseCase.searchNews(query)
    }.collectAsState(initial = DomainResult.Idle)

    private var _newsDetailState: Channel<String> = Channel(Channel.CONFLATED)

    val newsDetailFlow: Flow<String?> =
        _newsDetailState.receiveAsFlow().distinctUntilChanged()

    fun setNewsDetail(data: String) {
        viewModelScope.launch {
            _newsDetailState.send(data)
        }
    }

    @Composable
    fun NewsToolbar(
        screen: ScreensNavigation,
        toolBarText: String = "",
        navigationClick: () -> Unit = {},
        actionClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) = screensUseCase.DefaultToolbar(
        screen = screen,
        toolBarText = toolBarText,
        navigationClick = navigationClick,
        actionClick = actionClick,
        content = content
    )

    @Composable
    fun getNews(): State<DomainResult<List<News>>> = domainUseCase.getNews()

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
    fun NewsDetail(
        news: News, navigationClick: () -> Unit,
        actionClick: () -> Unit, modifier: Modifier
    ) =
        screensUseCase.NewsDetail(
            news = news,
            navigationClick = navigationClick,
            actionClick = actionClick,
            modifier = modifier
        )


    @Composable
    fun FailedScreen(text: String, modifier: Modifier) =
        screensUseCase.FailedScreen(text = text, modifier = modifier)

    @Composable
    fun LottieCirclingLoading(size: Dp, modifier: Modifier) =
        screensUseCase.LottieCirclingLoading(size, modifier)

    @Composable
    fun LottieFluidLoading(size: Dp, modifier: Modifier) =
        screensUseCase.LottieFluidLoading(size, modifier)

    @Composable
    fun Shimmer(itemSize: Int, modifier: Modifier) = screensUseCase.Shimmer(
        itemSize = itemSize,
        modifier = modifier
    )

    @Composable
    fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        onBack: () -> Unit
    ) = screensUseCase.BackHandler(backDispatcher = backDispatcher, enabled = true, onBack = onBack)
}
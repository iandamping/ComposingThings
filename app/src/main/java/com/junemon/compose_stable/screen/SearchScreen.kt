package com.junemon.compose_stable.screen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.presentation.model.SearchScreenState
import com.junemon.compose_stable.core.presentation.screens.SearchView
import com.junemon.compose_stable.navigation.ScreensNavigation
import com.junemon.compose_stable.util.Constant.EMPTY_NEWS
import com.junemon.compose_stable.util.Constant.SEARCH_NEWS
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Ian Damping on 29,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalUnitApi
@Composable
fun ComposeSearchScreen(
    sharedViewModel: ActivityRetainViewModel,
    searchViewModel: SearchMviViewModel,
    composableViewModel: ComposableViewModel,
    navController: NavHostController,
    modifier: Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    val searchNewsFlowLifecycleAware = remember(searchViewModel.state, lifecycleOwner) {
        searchViewModel.state.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val userQueryFlowLifecycleAware = remember(searchViewModel.userQuery, lifecycleOwner) {
        searchViewModel.userQuery
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val searchNews by searchNewsFlowLifecycleAware.collectAsState(initial = SearchScreenState.initial())
    val userQueryResults by userQueryFlowLifecycleAware.collectAsState(initial = "")

    SearchView(
        value = userQueryResults,
        onValueChange = { query ->
            searchViewModel.setSearchState(query)
        }) {
        when {
            searchNews.isLoading -> composableViewModel.FailedScreen(
                text = SEARCH_NEWS,
                modifier = modifier
            )
            searchNews.failedMessage.isNotEmpty() -> composableViewModel.FailedScreen(
                text = searchNews.failedMessage,
                modifier = modifier
            )
            searchNews.data.isNotEmpty() -> composableViewModel.ListNews(
                news = searchNews.data,
                modifier = modifier,
                newsSelect = {
                    sharedViewModel.switchScreenNewsDetail(Gson().toJson(it))
                    navController.navigate(ScreensNavigation.LoadDetail().name)
                })
            searchNews.data.isEmpty() -> composableViewModel.FailedScreen(
                text = EMPTY_NEWS,
                modifier = modifier
            )
        }
    }


    LaunchedEffect(key1 = "search", block = {
        searchViewModel.userQuery.onEach { result ->
            if (result.isEmpty()){
                searchViewModel.setToIdle()
            }
        }.launchIn(this)
    })


    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    composableViewModel.BackHandler(backDispatcher = backDispatcher) {
        navController.navigateUp()
    }


}
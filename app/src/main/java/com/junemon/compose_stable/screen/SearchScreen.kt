package com.junemon.compose_stable.screen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.core.presentation.screens.SearchView
import com.junemon.compose_stable.navigation.ScreensNavigation
import kotlinx.coroutines.flow.collect
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
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val searchFlowLifecycleAware = remember(viewModel.getSearchState(), lifecycleOwner) {
        viewModel.getSearchState()
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val searchStateFlowLifecycleAware = remember(viewModel.searchState, lifecycleOwner) {
        viewModel.searchState
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val searchResults by searchFlowLifecycleAware.collectAsState(initial = DomainResult.Idle)
    val searchStateResults by searchStateFlowLifecycleAware.collectAsState(initial = "")

    SearchView(
        value = searchStateResults,
        isSearched = viewModel.searchLoadingState.value,
        onValueChange = { query ->
            viewModel.setSearchState(query)
        }) {
        when (searchResults) {
            is DomainResult.Data -> {

                if ((searchResults as DomainResult.Data<List<News>>).data.isEmpty()) {
                    with(viewModel){
                        setLoadingSearchState(true)
                        FailedScreen(text = "News not find", modifier = modifier)
                    }


                } else {
                    viewModel.ListNews(
                        news = (searchResults as DomainResult.Data<List<News>>).data,
                        modifier = modifier,
                        newsSelect = {
                            viewModel.setNewsDetail(Gson().toJson(it))
                            navController.navigate(ScreensNavigation.LoadDetail().name)
                        })
                    viewModel.setLoadingSearchState(true)
                }

            }
            is DomainResult.Error -> {
                with(viewModel) {
                    setLoadingSearchState(true)
                    FailedScreen(
                        text = (searchResults as DomainResult.Error).message,
                        modifier = modifier
                    )
                }
            }
            is DomainResult.Idle -> {
                viewModel.setLoadingSearchState(true)
            }
        }
    }


    LaunchedEffect(key1 = "search", block = {
        viewModel.searchState.onEach { result ->
            if (result.isNotEmpty()) {
                viewModel.setLoadingSearchState(false)
            }
        }.launchIn(this)
    })

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    viewModel.BackHandler(backDispatcher = backDispatcher) {
        navController.navigateUp()
    }


}
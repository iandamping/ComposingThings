package com.junemon.compose_stable.screen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.junemon.compose_stable.core.domain.model.DomainResult
import com.junemon.compose_stable.core.presentation.screens.SearchView
import com.junemon.compose_stable.navigation.ScreensNavigation
import timber.log.Timber


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
        viewModel.getSearchState().flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val searchResults by searchFlowLifecycleAware.collectAsState(initial = DomainResult.Loading)

    SearchView(value = viewModel.searchState.collectAsState().value, onValueChange = { query ->
        viewModel.setSearchState(query)
    }) {
        when (val result = searchResults) {
            is DomainResult.Data -> {
                if (result.data.isEmpty()){
                    viewModel.FailedScreen(text = "News not find", modifier = modifier)
                }else {
                    viewModel.ListNews(
                        news = result.data,
                        modifier = modifier,
                        newsSelect = {
                            viewModel.setNewsDetail(Gson().toJson(it))
                            navController.navigate(ScreensNavigation.LoadDetail().name)
                        })
                }

            }
            is DomainResult.Error -> {
                viewModel.FailedScreen(text = result.message, modifier = modifier)
            }
            is DomainResult.Loading -> {
                viewModel.LottieCirclingLoading(200.dp, modifier)
            }
            is DomainResult.Idle -> {
                Timber.e("idle state")
            }
        }
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    viewModel.BackHandler(backDispatcher = backDispatcher) {
        navController.navigateUp()
    }


}
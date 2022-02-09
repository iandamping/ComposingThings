package com.junemon.compose_stable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(modifier: Modifier = Modifier) {
    val tabs = listOf(
        stringResource(id = R.string.tab_1),
        stringResource(id = R.string.tab_2)
    )
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier.fillMaxSize(), scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) {
        Column {
            TabRow(backgroundColor = Color.White,
                contentColor = Color.Blue,
                selectedTabIndex = pagerState.currentPage,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset(
                            pagerState,
                            it
                        )
                    )
                }
            ) {
                tabs.forEachIndexed { index, tabItem ->
                    LeadingIconTab(
                        icon = {
                               when(index) {
                                   0 -> Icon(imageVector = Icons.Default.Done, contentDescription = "tab1 image")
                                   else -> Icon(imageVector = Icons.Default.Delete, contentDescription = "tab1 image")
                               }
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = tabItem) })
                }
            }


            HorizontalPager(state = pagerState, count = tabs.size) { page ->
                when (page) {
                    0 -> ScreenA()
                    else -> ScreenB()
                }

            }
        }

    }
}
package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.junemon.compose_stable.R
import com.junemon.compose_stable.core.domain.model.response.News
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class ScreensUseCaseImpl @Inject constructor() : ScreensUseCase {

    @ExperimentalUnitApi
    @Composable
    override fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier) {
        LazyColumn(modifier.padding(Dp(8f))) {
            items(items = news) { singleNews ->
                NewsItem(singleNews = singleNews, newsSelect = newsSelect, modifier = modifier)
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    override fun NewsDetail(
        news: News,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        modifier: Modifier
    ) {
        NewsDetailToolbar(
            toolBarText = news.sourceName,
            navigationClick = navigationClick,
            actionClick = actionClick
        ) {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = news.newsTitle
                )
                Spacer(modifier = modifier.height(12.dp))

                Text(
                    style = MaterialTheme.typography.caption,
                    text = news.sourceName
                )
                Spacer(modifier = modifier.height(12.dp))

                Image(
                    painter = rememberImagePainter(news.newsImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                )

                Spacer(modifier = modifier.height(12.dp))

                Text(
                    style = MaterialTheme.typography.body2,
                    text = news.newsContent,
                    modifier = modifier.wrapContentSize()
                )
            }
        }
    }

    @Composable
    override fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean,
        onBack: () -> Unit
    ) {
        // Safely update the current `onBack` lambda when a new one is provided
        val currentOnBack by rememberUpdatedState(onBack)

        // Remember in Composition a back callback that calls the `onBack` lambda
        val backCallback = remember {
            // Always intercept back events. See the SideEffect for
            // a more complete version
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    currentOnBack()
                }
            }
        }

        // On every successful composition, update the callback with the `enabled` value
        // to tell `backCallback` whether back events should be intercepted or not
        SideEffect {
            backCallback.isEnabled = enabled
        }

        // If `backDispatcher` changes, dispose and reset the effect
        DisposableEffect(backDispatcher) {
            // Add callback to the backDispatcher
            backDispatcher.addCallback(backCallback)

            // When the effect leaves the Composition, remove the callback
            onDispose {
                backCallback.remove()
            }
        }


    }

    @ExperimentalUnitApi
    @Composable
    private fun DefaultToolbar(
        navigationClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        Scaffold(
            // below line we are
            // creating a top bar.
            topBar = {
                TopAppBar(
                    // in below line we are
                    // adding title to our top bar.
                    title = {
                        // inside title we are
                        // adding text to our toolbar.
                        Text(
                            style = MaterialTheme.typography.h6,
                            text = stringResource(id = R.string.app_name)
                        )
                    },
                    navigationIcon = {
                        // navigation icon is use
                        // for drawer icon.
                        IconButton(onClick = navigationClick) {
                            // below line is use to
                            // specify navigation icon.
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    // below line is use to give background color
                    backgroundColor = colorResource(id = R.color.white),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.Black,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 12.dp
                )
            }, content = content
        )
    }

    @ExperimentalUnitApi
    @Composable
    private fun NewsDetailToolbar(
        toolBarText: String,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        // theme for our app.
        Scaffold(
            // below line we are
            // creating a top bar.
            topBar = {
                TopAppBar(
                    // in below line we are
                    // adding title to our top bar.
                    title = {
                        // inside title we are
                        // adding text to our toolbar.
                        Text(
                            style = MaterialTheme.typography.h6,
                            text = toolBarText
                        )
                    },
                    navigationIcon = {
                        // navigation icon is use
                        // for drawer icon.
                        IconButton(onClick = navigationClick) {
                            // below line is use to
                            // specify navigation icon.
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = actionClick) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    // below line is use to give background color
                    backgroundColor = colorResource(id = R.color.white),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.Black,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 8.dp
                )
            }, content = content
        )
    }

    @Composable
    @ExperimentalUnitApi
    private fun NewsItem(singleNews: News, newsSelect: (News) -> Unit, modifier: Modifier) {
        Row(modifier.clickable {
            newsSelect(singleNews)
        }) {
            Column(
                modifier = modifier
                    .weight(2f)
                    .height(Dp(150F)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = singleNews.newsTitle,
                    color = Color.Black,
                    fontSize = TextUnit(value = 16F, TextUnitType.Sp),
                    modifier = modifier.padding(
                        bottom = Dp(8F),
                        start = Dp(8F),
                        end = Dp(8F)
                    )
                )
                Row {
                    Text(
                        text = singleNews.sourceName,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(Dp(4F))
                            .weight(1f)
                    )

                    DropDownNewsItem(modifier = modifier, selectedItem = {
                        Timber.e("selected item : $it")
                    })

                }
            }

            Image(
                painter = rememberImagePainter(singleNews.newsImage, builder = {
                    crossfade(true)
                }), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .weight(1f)
                    .padding(Dp(4f))
                    .clip(RoundedCornerShape(8.dp))

            )
        }
        Spacer(modifier = modifier.height(Dp(8f)))
    }

    @Composable
    private fun DropDownNewsItem(selectedItem: (String) -> Unit, modifier: Modifier) {
        val expanded = remember { mutableStateOf(false) }
        Box(
            modifier
                .wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("First item clicked")
                }) {
                    Text("First Item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Second item clicked")
                }) {
                    Text("Second item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Third item clicked")
                }) {
                    Text("Third item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Fourth item clicked")
                }) {
                    Text("Fourth item")
                }
            }
        }
    }
}

package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    override fun NewsDetail(news: News, modifier: Modifier) {
        Column(modifier = modifier.padding(Dp(8F))) {
            Image(
                painter = rememberImagePainter(news.newsImage),
                contentDescription = null,
                modifier = modifier
                    .height(Dp(300F))
                    .fillMaxWidth()
            )

            Spacer(modifier = modifier.height(Dp(8f)))
            Text(
                text = news.newsTitle,
                color = Color.Black,
                fontSize = TextUnit(value = 22F, TextUnitType.Sp)
            )
            Spacer(modifier = modifier.height(Dp(8f)))
            Text(
                text = news.newsDescription, fontSize = TextUnit(value = 16F, TextUnitType.Sp),
                modifier = modifier.fillMaxWidth()
            )
        }
    }

    @ExperimentalUnitApi
    @Composable
    override fun DefaultToolbar(navigationClick: () -> Unit, modifier: Modifier) {
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
                            text = stringResource(id = R.string.app_name),
                            // below line is use
                            // to give text color.
                            color = Color.Black
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
                    backgroundColor = colorResource(id = R.color.purple_200),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.White,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 12.dp
                )
            }){

        }
    }

    @ExperimentalUnitApi
    @Composable
    override fun NewsDetailToolbar(
        news: News,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        modifier: Modifier
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
                            text = news.sourceName,
                            // below line is use
                            // to give text color.
                            color = Color.White
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
                    backgroundColor = colorResource(id = R.color.purple_200),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.White,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 12.dp
                )
            }){

        }
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
                    .height(Dp(150F))
                    .width(Dp(150F))
                    .weight(1f)
                    .padding(Dp(4f))
                    .clip(RoundedCornerShape(Dp(8F)))

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

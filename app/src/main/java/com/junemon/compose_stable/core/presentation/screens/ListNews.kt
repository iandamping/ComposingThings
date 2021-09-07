package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import coil.compose.rememberImagePainter
import com.junemon.compose_stable.core.domain.model.response.News

@ExperimentalUnitApi
@Composable
fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier) {
    LazyColumn(modifier.padding(Dp(8f))) {
        items(items = news) { singleNews ->
            Row(modifier.clickable {
                newsSelect(singleNews)
            }) {
                Image(
                    painter = rememberImagePainter(singleNews.newsImage, builder = {
                        crossfade(true)
                    }), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(Dp(150F))
                        .width(Dp(150F))
                )
                Column {
                    Text(
                        text = singleNews.newsTitle,
                        color = Color.Black,
                        fontSize = TextUnit(value = 16F, TextUnitType.Sp),
                        modifier = modifier.padding(bottom = Dp(8F),start = Dp(8F),end = Dp(8F))
                    )
                    // Spacer(modifier = modifier.height(Dp(8f)))
                    Text(
                        text = singleNews.newsAuthor,
                        color = Color.Gray,
                        modifier = modifier.padding(Dp(8F))
                    )
                }

            }
            Spacer(modifier = Modifier.height(Dp(8f)))

        }
    }
}
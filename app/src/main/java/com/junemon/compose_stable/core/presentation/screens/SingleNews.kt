package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import coil.compose.rememberImagePainter
import com.junemon.compose_stable.core.domain.model.response.News

@ExperimentalUnitApi
@Composable
fun SingleNews(news: News, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(Dp(8F))) {
        Image(
            painter = rememberImagePainter(news.newsImage),
            contentDescription = null,
            modifier = Modifier
                .height(Dp(300F))
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dp(8f)))
        Text(
            text = news.newsTitle,
            color = Color.Black,
            fontSize = TextUnit(value = 22F, TextUnitType.Sp)
        )
        Spacer(modifier = Modifier.height(Dp(8f)))
        Text(
            text = news.newsDescription, fontSize = TextUnit(value = 16F, TextUnitType.Sp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
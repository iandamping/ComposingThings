package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import coil.compose.rememberImagePainter
import com.junemon.compose_stable.core.presentation.DummyModel
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme

/**
 * Created by Ian Damping on 07,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Preview(showBackground = true)
@ExperimentalUnitApi
@Composable
fun DefaultPreview() {
    ComposingThingsTheme {
        LazyColumn(Modifier.padding(Dp(8f))) {
            items(items = DummyModel.LIST_DUMMY_NEWS) { singleNews ->
                Row(Modifier.fillMaxSize()) {

                    Column(modifier = Modifier.weight(2.5f).height(Dp(150F)),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = singleNews.newsTitle,
                            color = Color.Black,
                            fontSize = TextUnit(value = 16F, TextUnitType.Sp),
                            modifier = Modifier.padding(
                                Dp(4F)
                            )
                        )
                        // Spacer(Modifier = Modifier.height(Dp(8f)))
                        Text(
                            text = singleNews.newsAuthor,
                            color = Color.Gray,
                            modifier = Modifier.padding(Dp(8F))
                        )
                    }

                    Image(
                        painter = rememberImagePainter(singleNews.newsImage, builder = {
                            crossfade(true)
                        }), contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(Dp(150F))
                            .width(Dp(150F))
                            .clip(RoundedCornerShape(Dp(8F)))
                            .weight(1f)

                    )

                }
                Spacer(modifier = Modifier.height(Dp(8f)))

            }
        }
    }
}




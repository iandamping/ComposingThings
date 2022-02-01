package com.junemon.compose_stable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun LazyList() {
    val listSize = 100
    val scope = rememberCoroutineScope()
    val scrollingState = rememberLazyListState()

    Column {
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = {
                scope.launch {
                    scrollingState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll to top")
            }

            Button(onClick = { scope.launch {
                scrollingState.animateScrollToItem(listSize -1)
            } }) {
                Text(text = "Scroll to Bottom")
            }
        }

        LazyColumn(state = scrollingState) {
            items(listSize) {
                ImageLazyItem(index = it)
            }
        }
    }

}

@Composable
fun ImageLazyItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(50.dp),
            painter =
            rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ), contentDescription = "Android Logo"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Item at $index", style = MaterialTheme.typography.subtitle1)
    }
}
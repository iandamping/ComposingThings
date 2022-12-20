package com.junemon.compose_stable.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.R


@Composable
fun Body(modifier: Modifier = Modifier, headerHeight: Dp) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(headerHeight))

        repeat(5) {
            Text(
                text = stringResource(R.string.lorem_ipsum),
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .background(Color(0XFF161616))
                    .padding(16.dp)
            )
        }
    }
}
package com.junemon.compose_stable.util

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme
import timber.log.Timber


/**
 * Created by Ian Damping on 27,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposingThingsTheme {
        GreetingButton()
    }
}

@Composable
fun GreetingButton() {
    Button(onClick = {
        Timber.e("Button clicked")
    }) {
        Text(text = "Start")
    }
}
package com.junemon.compose_stable.core.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme


/**
 * Created by Ian Damping on 24,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Composable
fun ComposingWithTheme(content: @Composable () -> Unit) {
    ComposingThingsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content.invoke()
        }
    }
}

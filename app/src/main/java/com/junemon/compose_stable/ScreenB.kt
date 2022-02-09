package com.junemon.compose_stable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun ScreenB() {
    Text(text = stringResource(id = R.string.tab_2))
}
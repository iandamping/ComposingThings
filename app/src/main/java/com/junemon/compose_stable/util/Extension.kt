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
data class GenericPair<out A, out B>(val data1: A, val data2: B)

data class GenericTriple<out A, out B, out C>(val data1: A, val data2: B, val data3:C)


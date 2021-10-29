package com.junemon.compose_stable.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.junemon.compose_stable.DynamicContent


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
object Constant {
    const val FIREBASE_NODE = "TextNode"
    const val ERROR_PUSH_FIREBASE = "Error happen"

    val listOfDynamicContent = listOf(
        DynamicContent(
            colorValue = 0xFF03DAC5,
            fontSize = 16,
            text = "Selamat Datang Kawan"
        )
    )
}
package com.junemon.compose_stable

import androidx.annotation.DrawableRes

data class FilterItem(
    @DrawableRes val filterIcon: Int,
    val filterText: String
)

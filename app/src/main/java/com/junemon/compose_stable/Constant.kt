package com.junemon.compose_stable

object Constant {
    const val FILTER_0 = "Everything"
    const val FILTER_1 = "Breakfast"
    const val FILTER_2 = "Lunch"
    const val FILTER_3 = "Dinner"
    const val FILTER_4 = "Brunch"
    const val FILTER_5 = "Supper"

    val FILTER_ITEM = listOf<FilterItem>(
        FilterItem(
            filterIcon = R.drawable.ic_filter_0,
            filterText = FILTER_0
        ),
        FilterItem(
            filterIcon = R.drawable.ic_filter_1,
            filterText = FILTER_1
        ),
        FilterItem(
            filterIcon = R.drawable.ic_filter_2,
            filterText = FILTER_2
        ),
        FilterItem(
            filterIcon = R.drawable.ic_filter_3,
            filterText = FILTER_3
        ),
        FilterItem(
            filterIcon = R.drawable.ic_filter_4,
            filterText = FILTER_4
        ),
        FilterItem(
            filterIcon = R.drawable.ic_filter_5,
            filterText = FILTER_5
        ),
    )
}
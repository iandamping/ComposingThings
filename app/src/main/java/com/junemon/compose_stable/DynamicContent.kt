package com.junemon.compose_stable

import com.google.gson.annotations.SerializedName


/**
 * Created by Ian Damping on 29,October,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class DynamicContent(
    @field:SerializedName("colorValue") val colorValue: Long?,
    @field:SerializedName("fontSize") val fontSize: Int?,
    @field:SerializedName("text") val text: String?
) {
    constructor() : this(
        null,
        null,
        null,
    )
}

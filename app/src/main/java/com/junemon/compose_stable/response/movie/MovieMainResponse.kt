package com.junemon.compose_stable.response.movie

import com.google.gson.annotations.SerializedName

data class MovieMainResponse<T>(
    @field:SerializedName("results") val results: List<T>
)
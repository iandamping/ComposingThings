package com.junemon.compose_stable.core.datasource.response.movie

import com.google.gson.annotations.SerializedName

data class MovieMainResponse(
    @field:SerializedName("results") val results: List<MovieResponse>
)
package com.junemon.compose_stable.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("vote_average") val voteAverage: Double,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("poster_path") val poster_path: String,
    @field:SerializedName("original_title") val originalTitle: String,
    @field:SerializedName("backdrop_path") val backdrop_path: String,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("vote_count") val vote_count: Int,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("original_language") val original_language: String,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("release_date") val release_date: String
)
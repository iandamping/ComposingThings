package com.junemon.compose_stable.datasource.response.movie

import com.google.gson.annotations.SerializedName
import com.junemon.compose_stable.datasource.network.NetworkConstant.imageFormatter
import com.junemon.compose_stable.domain.model.Dto
import com.junemon.compose_stable.domain.model.movie.MovieDetail

data class MovieDetailResponse(
    @field:SerializedName("backdrop_path") val backdropPath: String,
    @field:SerializedName("budget") val budget: Int,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("poster_path") val posterPath: String,
    @field:SerializedName("tagline") val tagline: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("vote_average") val voteAverage: Double,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("imdb_id") val imdb_id: String,
    @field:SerializedName("original_language") val original_language: String,
    @field:SerializedName("original_title") val original_title: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("release_date") val release_date: String,
    @field:SerializedName("revenue") val revenue: String,
    @field:SerializedName("runtime") val runtime: Int,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("vote_count") val vote_count: Int
) : Dto {

    override fun toDomain(): MovieDetail {
        return MovieDetail(
            localId = null,
            movieId = id,
            backdropPath = imageFormatter + backdropPath,
            overview = overview,
            title = title
        )
    }
}
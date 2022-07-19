package com.junemon.compose_stable.domain.model.movie

import com.junemon.compose_stable.domain.model.Domain

data class MovieDetail(
    val localId:Int?,
    val movieId:Int,
    val backdropPath: String,
    val overview: String,
    val title: String,
): Domain

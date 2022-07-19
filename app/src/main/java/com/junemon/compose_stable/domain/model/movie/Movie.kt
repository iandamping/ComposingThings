package com.junemon.compose_stable.domain.model.movie

import com.junemon.compose_stable.domain.model.Domain

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String
): Domain

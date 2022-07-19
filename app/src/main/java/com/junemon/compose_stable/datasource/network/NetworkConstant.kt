package com.junemon.compose_stable.datasource.network

object NetworkConstant {
    const val cacheSize = 10L * 1024 * 1024 // 10MB
    const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"
    const val GET_POKEMON = "pokemon"
    const val GET_POKEMON_CHARACTERISTIC = "characteristic"
    const val GET_POKEMON_AREAS = "encounters"
    const val EMPTY_DATA = "EMPTY"
    const val NETWORK_ERROR = "NETWORK ERROR"
    const val POKEMON_STARTING_OFFSET = 0
    const val POKEMON_OFFSET = 20
    const val NETWORK_PAGE_SIZE = 2
    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    const val GET_ALL_NEWS = "everything"
    const val GET_TOP_HEADLINES = "top-headlines"
    const val GET_NEWS_DETAIL = "news/get-detail"
    const val NEWS_API_KEY = "25bd4439e7564164a9ab567975428415"
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
    const val POPULAR_MOVIE = "movie/popular"
    const val DETAIL_MOVIE = "movie/"
    const val MOVIE_API_KEY = "9986464dc7ff8e83569e65a98dc7b3b6"
    const val imageFormatter = "https://image.tmdb.org/t/p/w500"
}
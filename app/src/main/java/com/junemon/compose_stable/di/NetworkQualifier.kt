package com.junemon.compose_stable.di

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PokemonApiInterface

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MovieApiInterface

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NewsApiInterface

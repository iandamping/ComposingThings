package com.junemon.compose_stable.core.di.module

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationDefaultScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationIoScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationMainScope
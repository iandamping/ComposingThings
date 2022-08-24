package com.junemon.compose_stable.di.dispatcher

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BackgroundDispatcherQualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcherQualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultApplicationCoroutineScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoApplicationCoroutineScope
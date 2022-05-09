package com.junemon.compose_stable.core.di.module

import com.junemon.compose_stable.core.presentation.screens.ScreensUseCase
import com.junemon.compose_stable.core.presentation.screens.ScreensUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface PresentationModule {

    @Binds
    fun bindsScreenUseCase(useCase: ScreensUseCaseImpl): ScreensUseCase

}
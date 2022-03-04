package com.junemon.compose_stable.navigation

import com.junemon.compose_stable.navigation.ConstantNavigation.ScreensNavigationConstant.DETAIL_SCREEN
import com.junemon.compose_stable.navigation.ConstantNavigation.ScreensNavigationConstant.HOME_SCREEN

sealed class ConstantNavigation {
    data class LoadHome(val name: String = HOME_SCREEN) : ConstantNavigation()
    data class LoadDetail(val name: String = DETAIL_SCREEN) : ConstantNavigation()

    private object ScreensNavigationConstant {
        const val HOME_SCREEN = "Load Home"
        const val DETAIL_SCREEN = "Load Detail"
    }
}
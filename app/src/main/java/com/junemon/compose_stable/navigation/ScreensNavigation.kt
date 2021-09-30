package com.junemon.compose_stable.navigation

import com.junemon.compose_stable.navigation.ScreensNavigation.ScreensNavigationConstant.DETAIL_SCREEN
import com.junemon.compose_stable.navigation.ScreensNavigation.ScreensNavigationConstant.HOME_SCREEN
import com.junemon.compose_stable.navigation.ScreensNavigation.ScreensNavigationConstant.SEARCH_SCREEN
import com.junemon.compose_stable.navigation.ScreensNavigation.ScreensNavigationConstant.SPLASH_SCREEN

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class ScreensNavigation {
    data class LoadSplash(val name: String = SPLASH_SCREEN) : ScreensNavigation()
    data class LoadHome(val name: String = HOME_SCREEN) : ScreensNavigation()
    data class LoadDetail(val name: String = DETAIL_SCREEN) : ScreensNavigation()
    data class LoadSearch(val name: String = SEARCH_SCREEN) : ScreensNavigation()

    private object ScreensNavigationConstant {
        const val SPLASH_SCREEN = "Load Splash"
        const val HOME_SCREEN = "Load Home"
        const val DETAIL_SCREEN = "Load Detail"
        const val SEARCH_SCREEN = "Load Search"
    }
}
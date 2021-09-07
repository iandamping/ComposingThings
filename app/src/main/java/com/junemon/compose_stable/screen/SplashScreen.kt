package com.junemon.compose_stable.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.junemon.compose_stable.R
import com.junemon.compose_stable.navigation.ScreensNavigation
import com.junemon.compose_stable.util.Constant.SPLASH_DURATION
import kotlinx.coroutines.delay

/**
 * Created by Ian Damping on 05,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalAnimationApi
@Composable
fun ComposeSplashScreen(
    navController: NavHostController,
    modifier: Modifier
) {
    val visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = fadeOut()
    ) {
        Image(
            painter = painterResource(id = R.drawable.muaythai),
            contentDescription = null,
            modifier = modifier.fillMaxSize()
        )
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(SPLASH_DURATION)
        navController.navigate(ScreensNavigation.LoadHome().name)
    })
}


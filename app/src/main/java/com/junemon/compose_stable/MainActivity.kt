package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.junemon.compose_stable.navigation.NavigationHost
import com.junemon.compose_stable.screen.NewsViewModel
import com.junemon.compose_stable.util.ComposingWithTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModels()

    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingWithTheme {
                val navController = rememberNavController()
                NavigationHost(navController = navController,viewModel = vm)
            }
        }
    }
}

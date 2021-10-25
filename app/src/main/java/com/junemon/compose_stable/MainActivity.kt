package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.junemon.compose_stable.navigation.NavigationHost
import com.junemon.compose_stable.screen.ActivityRetainViewModel
import com.junemon.compose_stable.screen.ComposableViewModel
import com.junemon.compose_stable.screen.HomeMviViewModel
import com.junemon.compose_stable.screen.SearchMviViewModel
import com.junemon.compose_stable.util.ComposingWithTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val composeVm: ComposableViewModel by viewModels()
    private val homeMviVm: HomeMviViewModel by viewModels()
    private val searchMviVm: SearchMviViewModel by viewModels()
    private val activityRetainVm: ActivityRetainViewModel by viewModels()

    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingWithTheme {
                val navController = rememberNavController()
                NavigationHost(
                    sharedViewModel = activityRetainVm,
                    navController = navController,
                    composeViewModel = composeVm,
                    searchViewModel = searchMviVm,
                    homeViewModel = homeMviVm
                )
            }
        }
    }
}

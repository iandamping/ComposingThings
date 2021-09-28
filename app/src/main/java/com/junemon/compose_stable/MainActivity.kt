package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.junemon.compose_stable.core.presentation.screens.SearchView
import com.junemon.compose_stable.navigation.NavigationHost
import com.junemon.compose_stable.screen.NewsViewModel
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme
import com.junemon.compose_stable.util.ComposingWithTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModels()

    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingWithTheme {
//                SearchView(value = vm.searchState.value, onValueChange = { vm.setSearchState(it) })
//                Timber.e("search value : ${vm.searchState.value}")
                val navController = rememberNavController()
                NavigationHost(navController = navController,viewModel = vm)
            }
        }
    }
}

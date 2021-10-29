package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val dynamicVm: DynamicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingThingsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(dynamicVm)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: DynamicViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycle = remember(viewModel.getDynamicContent(), lifecycleOwner) {
        viewModel.getDynamicContent().flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val dynamicContent by flowLifecycle.collectAsState(initial = null)

    dynamicContent?.let { value ->

        when (value) {
            is DomainSource.Fail -> Greeting(value.message ?: "aaa")
            is DomainSource.Result -> {
                val testing = value.data.first()
                Text(
                    text = testing.text ?: "Testing",
                    fontSize = testing.fontSize?.sp ?: 16.sp,
                    color = Color(testing.colorValue ?: 0xFF6200EE)
                )
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name, fontSize = 16.sp, color = Color.Gray)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposingThingsTheme {
        Greeting("Android")
    }
}


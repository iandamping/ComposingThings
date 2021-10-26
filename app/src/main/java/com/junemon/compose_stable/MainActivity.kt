package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.junemon.compose_stable.screen.TimerScreen
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val boxingVm: BoxingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingThingsTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                boxingVm.setRoundTime(0)

                val timeTickingLifecycle = remember(boxingVm.currentTime, lifecycleOwner) {
                    boxingVm.currentTime.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val timeTickingInFloatLifecycle =
                    remember(boxingVm.currentTimeInFloat, lifecycleOwner) {
                        boxingVm.currentTimeInFloat.flowWithLifecycle(
                            lifecycleOwner.lifecycle,
                            Lifecycle.State.STARTED
                        )
                    }
                val timerValueLifecycle = boxingVm.roundTimeValue.observeAsState()


                timerValueLifecycle.value?.let {
                    boxingVm.startTimer(it) {
                        Timber.e("Nothing")
                    }
                }

                val timeTicking by timeTickingLifecycle.collectAsState(null)
                val timeTickingInFloat by timeTickingInFloatLifecycle.collectAsState(null)


                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TimerScreen(
                        timeTickingForDialog = timeTickingInFloat,
                        timeTickingForText = timeTicking,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(357.dp)
                            .padding(12.dp)
                    )
                }
            }
        }
    }
}



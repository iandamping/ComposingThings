package com.junemon.compose_stable

import android.os.Bundle
import android.view.WindowManager
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
import com.junemon.compose_stable.screen.*
import com.junemon.compose_stable.ui.theme.TimerTheme
import com.junemon.compose_stable.util.TimerConstant
import com.junemon.compose_stable.util.TimerConstant.ONE_SECOND
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val boxingVm: BoxingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            TimerTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                val pauseTimeLifecycle = remember(boxingVm.pausedTime, lifecycleOwner) {
                    boxingVm.pausedTime.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val pauseTime by pauseTimeLifecycle.collectAsState(initial = null)

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TimerScreen(
                            timerViewModel = boxingVm,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(357.dp)
                                .padding(12.dp)
                        )

                        RestTimeRadioButton { restTime ->
                            boxingVm.setRestTime(restTime)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StartTimerButton {
                                if (pauseTime!=null){
                                    boxingVm.startTimer(pauseTime!!.toInt() * ONE_SECOND)
                                }else {
                                    boxingVm.startTimer(TimerConstant.setCustomMinutes(25))
                                }
                            }
                            PauseTimerButton {
                                boxingVm.pauseTimer()
                            }
                            ResetTimerButton {
                                boxingVm.cancelAllTimer()
                            }
                        }
                    }
                }
            }
        }
    }
}









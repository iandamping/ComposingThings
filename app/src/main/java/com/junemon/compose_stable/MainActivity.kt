package com.junemon.compose_stable

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.screen.*
import com.junemon.compose_stable.ui.theme.TimerTheme
import com.junemon.compose_stable.util.TimerConstant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val boxingVm: BoxingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            TimerTheme {
                val currentTime by boxingVm.currentTime.observeAsState()
                val isTimerRunning by boxingVm.isTimerRunning.observeAsState(false)
                val pauseTime by boxingVm.pausedTime.observeAsState()
                val restTime by boxingVm.restTime.observeAsState(3)
                val timerState by boxingVm.timerState.observeAsState()

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TimerController(
                        timerViewModel = boxingVm,
                        isTimerRunning = isTimerRunning,
                        pauseTime = pauseTime,
                        restTime = restTime,
                        timerState = timerState
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TimerScreen(
                            timerViewModel = boxingVm,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(357.dp)
                                .padding(12.dp)
                        )

                        RestTimeRadioButton(
                            item = TimerConstant.listOfRestTime,
                            isRadioButtonEnabled = isTimerRunning
                        ) { restTime ->
                            boxingVm.setRestTime(restTime)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StartTimerButton {
                                with(boxingVm) {
                                    setTimerIsRunning(true)
                                    startRoundSound()
                                }

                            }
                            PauseTimerButton {
                                currentTime?.let {
                                    with(boxingVm) {
                                        setTimerIsRunning(false)
                                        pauseTimer(it)
                                    }
                                }
                            }
                            ResetTimerButton {
                                with(boxingVm) {
                                    setTimerIsRunning(false)
                                    cancelAllTimer()
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}












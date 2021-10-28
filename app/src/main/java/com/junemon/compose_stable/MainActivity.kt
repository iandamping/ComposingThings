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
import com.junemon.compose_stable.util.TimerConstant.ONE_SECOND
import com.junemon.compose_stable.util.TimerConstant.setCustomMinutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val boxingVm: BoxingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            TimerTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                val currentTimeLifecycle = remember(boxingVm.currentTime, lifecycleOwner) {
                    boxingVm.currentTime.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val pauseTimeLifecycle = remember(boxingVm.pausedTime, lifecycleOwner) {
                    boxingVm.pausedTime.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val restTimeLifecycle = remember(boxingVm.restTime, lifecycleOwner) {
                    boxingVm.restTime.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }

                val timerStateLifecycle = remember(boxingVm.timerState, lifecycleOwner) {
                    boxingVm.timerState.flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED
                    )
                }
                val currentTime by currentTimeLifecycle.collectAsState(null)
                val isTimerRunning by boxingVm.isTimerRunning.observeAsState()
                val pauseTime by pauseTimeLifecycle.collectAsState(initial = null)
                val restTime by restTimeLifecycle.collectAsState(initial = 3)
                val timerState by timerStateLifecycle.collectAsState(
                    TimerState.RoundTime(
                        setCustomMinutes(25)
                    )
                )
                isTimerRunning?.let { nonNull ->
                    if (nonNull) {
                        when (timerState) {
                            is TimerState.RestTime -> {
                                if (pauseTime != null) {
                                    boxingVm.startTimer(
                                        durationTime = pauseTime!!.toInt() * ONE_SECOND,
                                        durationTimes = setCustomMinutes(restTime),
                                        finishTicking = {
                                            with(boxingVm){
                                                startRoundSound()
                                                setRoundTimeState(25)
                                            }
                                        })
                                } else {
                                    boxingVm.startTimer(
                                        durationTime = (timerState as TimerState.RestTime).time,
                                        durationTimes = null,
                                        finishTicking = {
                                            with(boxingVm){
                                                startRoundSound()
                                                setRoundTimeState(25)
                                            }
                                        })
                                }
                            }
                            is TimerState.RoundTime -> {
                                if (pauseTime != null) {
                                    boxingVm.startTimer(
                                        durationTime = pauseTime!!.toInt() * ONE_SECOND,
                                        durationTimes = setCustomMinutes(25),
                                        finishTicking = {
                                            with(boxingVm) {
                                                endRoundSound()
                                                incrementPomodoroRound()
                                                setRestTimeState(restTime)
                                            }
                                        })
                                } else {
                                    boxingVm.startTimer(durationTime = (timerState as TimerState.RoundTime).time,
                                        durationTimes = null,
                                        finishTicking = {
                                            with(boxingVm) {
                                                endRoundSound()
                                                incrementPomodoroRound()
                                                setRestTimeState(restTime)
                                            }
                                        })
                                }
                            }
                        }
                    }
                }


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
                                with(boxingVm){
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
                                with(boxingVm){
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












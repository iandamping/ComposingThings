package com.junemon.compose_stable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.screen.*
import com.junemon.compose_stable.ui.theme.TimerTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val boxingVm: BoxingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {


                val timerValueLifecycle = boxingVm.roundTimeValue.observeAsState()


                timerValueLifecycle.value?.let {
                    boxingVm.startTimer(it) {
                        Timber.e("Nothing")
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
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StartTimerButton {
                                boxingVm.setRoundTime(25)
                            }
                            PauseTimerButton {

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









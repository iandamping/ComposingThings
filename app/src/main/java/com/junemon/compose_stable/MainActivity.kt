package com.junemon.compose_stable

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        SelectingRestRoundTimeAndRoundScreen(intervalVm = boxingVm)
                        TimerScreen(intervalVm = boxingVm)
                        WaringTimeRadioButton(intervalVm = boxingVm)
                        IntervalTimerButton(intervalVm = boxingVm)
                    }
                }
            }
        }
    }


}












package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.core.presentation.CostumShape
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme

/**
 * Created by Ian Damping on 07,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Preview(showBackground = true)
@ExperimentalUnitApi
@Composable
fun DefaultPreview() {
    ComposingThingsTheme {
//        ScreensUseCaseImpl().LottieLoading()
        Spacer(modifier = Modifier.fillMaxWidth()
            .height(500.dp)
            .graphicsLayer {
                clip = true
                shape = CostumShape()
                shadowElevation = 50.dp.toPx()
            })
    }
}




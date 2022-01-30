package com.junemon.compose_stable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingThingsTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    //rememberSaveable is to help survive config change
    var shouldShowOnBoarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowOnBoarding) {
        OnBoardingScreen {
            shouldShowOnBoarding = false
        }
    } else {
        Greetings()
    }
}


@Composable
fun OnBoardingScreen(onBoardingClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                onClick = onBoardingClicked,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}


@Composable
fun Greetings(listOfNames: List<String> = List(1000) { "position $it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(listOfNames) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val extraPadding by animateDpAsState(
        if (isExpanded) 48.dp else 0.dp, animationSpec =
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello !")
                Text(
                    text = name, style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector =  if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) stringResource(id = R.string.show_less) else stringResource(
                        id = R.string.show_more
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 340,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun Preview() {
    ComposingThingsTheme {
        MyApp()
    }

}


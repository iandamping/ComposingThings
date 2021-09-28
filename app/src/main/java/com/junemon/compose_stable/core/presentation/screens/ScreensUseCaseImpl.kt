package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.junemon.compose_stable.R
import com.junemon.compose_stable.core.domain.model.response.News
import com.junemon.compose_stable.navigation.ScreensNavigation
import com.junemon.compose_stable.ui.theme.ShimmerColorShades
import com.junemon.compose_stable.util.Constant
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class ScreensUseCaseImpl @Inject constructor() : ScreensUseCase {

    @ExperimentalUnitApi
    @Composable
    override fun ListNews(news: List<News>, newsSelect: (News) -> Unit, modifier: Modifier) {
        LazyColumn(modifier.padding(Dp(8f))) {
            items(items = news) { singleNews ->
                NewsItem(singleNews = singleNews, newsSelect = newsSelect, modifier = modifier)
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    override fun NewsDetail(
        news: News,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        modifier: Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())


        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = news.newsTitle
            )
            Spacer(modifier = modifier.height(12.dp))

            Text(
                style = MaterialTheme.typography.caption,
                text = news.sourceName
            )
            Spacer(modifier = modifier.height(12.dp))

            Image(
                painter = rememberImagePainter(news.newsImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            )

            Spacer(modifier = modifier.height(12.dp))

            Text(
                style = MaterialTheme.typography.body2,
                text = news.newsContent,
                modifier = modifier.wrapContentSize()
            )
        }
    }

    @Composable
    override fun FailedScreen(text: String, modifier: Modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_data),
                contentDescription = null,
                modifier = modifier.size(300.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(8.dp)
            )
        }
    }

    @Composable
    override fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean,
        onBack: () -> Unit
    ) {
        // Safely update the current `onBack` lambda when a new one is provided
        val currentOnBack by rememberUpdatedState(onBack)

        // Remember in Composition a back callback that calls the `onBack` lambda
        val backCallback = remember {
            // Always intercept back events. See the SideEffect for
            // a more complete version
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    currentOnBack()
                }
            }
        }

        // On every successful composition, update the callback with the `enabled` value
        // to tell `backCallback` whether back events should be intercepted or not
        SideEffect {
            backCallback.isEnabled = enabled
        }

        // If `backDispatcher` changes, dispose and reset the effect
        DisposableEffect(backDispatcher) {
            // Add callback to the backDispatcher
            backDispatcher.addCallback(backCallback)

            // When the effect leaves the Composition, remove the callback
            onDispose {
                backCallback.remove()
            }
        }


    }

    @Composable
    override fun DefaultToolbar(
        screen: ScreensNavigation,
        toolBarText: String,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        Scaffold(
            // below line we are
            // creating a top bar.
            topBar = {
                TopAppBar(
                    // in below line we are
                    // adding title to our top bar.
                    title = {
                        // inside title we are
                        // adding text to our toolbar.
                        when (screen) {
                            is ScreensNavigation.LoadHome -> {
                                Text(
                                    style = MaterialTheme.typography.h6,
                                    text = stringResource(id = R.string.app_name)
                                )
                            }
                            is ScreensNavigation.LoadDetail -> {
                                Text(
                                    style = MaterialTheme.typography.h6,
                                    text = toolBarText
                                )
                            }
                        }


                    },
                    navigationIcon = {
                        when (screen) {
                            is ScreensNavigation.LoadHome -> {
                                IconButton(onClick = navigationClick, enabled = false) {
                                    Icon(
                                        Icons.Filled.Home,
                                        contentDescription = null
                                    )
                                }

                            }
                            is ScreensNavigation.LoadDetail -> {
                                IconButton(onClick = navigationClick) {
                                    Icon(
                                        Icons.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        }


                    },
                    actions = {
                        when (screen) {
                            is ScreensNavigation.LoadHome -> {
                                IconButton(onClick = actionClick) {
                                    Icon(
                                        Icons.Filled.Search,
                                        contentDescription = null
                                    )
                                }

                            }
                            is ScreensNavigation.LoadDetail -> {
                                IconButton(onClick = actionClick) {
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Localized description"
                                    )
                                }

                            }
                        }

                    },
                    // below line is use to give background color
                    backgroundColor = colorResource(id = R.color.white),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.Black,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 12.dp
                )
            }, content = content
        )
    }

    @ExperimentalUnitApi
    @Composable
    private fun NewsDetailToolbar(
        toolBarText: String,
        navigationClick: () -> Unit,
        actionClick: () -> Unit,
        content: @Composable (PaddingValues) -> Unit
    ) {
        // theme for our app.
        Scaffold(
            // below line we are
            // creating a top bar.
            topBar = {
                TopAppBar(
                    // in below line we are
                    // adding title to our top bar.
                    title = {
                        // inside title we are
                        // adding text to our toolbar.
                        Text(
                            style = MaterialTheme.typography.h6,
                            text = toolBarText
                        )
                    },
                    navigationIcon = {
                        // navigation icon is use
                        // for drawer icon.
                        IconButton(onClick = navigationClick) {
                            // below line is use to
                            // specify navigation icon.
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = actionClick) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    // below line is use to give background color
                    backgroundColor = colorResource(id = R.color.white),

                    // content color is use to give
                    // color to our content in our toolbar.
                    contentColor = Color.Black,

                    // below line is use to give
                    // elevation to our toolbar.
                    elevation = 8.dp
                )
            }, content = content
        )
    }

    @Composable
    @ExperimentalUnitApi
    private fun NewsItem(singleNews: News, newsSelect: (News) -> Unit, modifier: Modifier) {
        Row(modifier.clickable {
            newsSelect(singleNews)
        }) {
            Column(
                modifier = modifier
                    .weight(2f)
                    .height(Dp(150F)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = singleNews.newsTitle,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    style = MaterialTheme.typography.h6,
                    modifier = modifier.padding(
                        bottom = Dp(8F),
                        start = Dp(8F),
                        end = Dp(8F)
                    ), overflow = TextOverflow.Ellipsis,
                    maxLines = 4
                )
                Row {
                    Text(
                        text = singleNews.sourceName,
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(Dp(4F))
                            .weight(1f)
                    )

                    DropDownNewsItem(modifier = modifier, selectedItem = {
                        Timber.e("selected item : $it")
                    })

                }
            }

            Image(
                painter = rememberImagePainter(singleNews.newsImage, builder = {
                    crossfade(true)
                }), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .weight(1f)
                    .padding(Dp(4f))
                    .clip(RoundedCornerShape(8.dp))

            )
        }
        Spacer(modifier = modifier.height(Dp(8f)))
    }

    @Composable
    private fun DropDownNewsItem(selectedItem: (String) -> Unit, modifier: Modifier) {
        val expanded = remember { mutableStateOf(false) }
        Box(
            modifier
                .wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("First item clicked")
                }) {
                    Text("First Item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Second item clicked")
                }) {
                    Text("Second item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Third item clicked")
                }) {
                    Text("Third item")
                }

                DropdownMenuItem(onClick = {
                    expanded.value = false
                    selectedItem("Fourth item clicked")
                }) {
                    Text("Fourth item")
                }
            }
        }
    }

    @Composable
    override fun LottieCirclingLoading(size: Dp, modifier: Modifier) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(Constant.CIRCLING_LOADING))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(composition, progress, Modifier.size(size))
        }
    }

    @Composable
    override fun LottieFluidLoading(size: Dp, modifier: Modifier) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(Constant.FLUID_LOADING))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(composition, progress, Modifier.size(size))
        }
    }

    @Composable
    override fun Shimmer(itemSize: Int, modifier: Modifier) {
        LazyColumn(Modifier.padding(Dp(8f))) {
            repeat(itemSize) {
                item {
                    ShimmerAnimation(modifier)
                }
            }
        }
    }

    @Composable
    private fun ShimmerAnimation(modifier: Modifier) {
        /**
        Create InfiniteTransition
        which holds child animation like [Transition]
        animations start running as soon as they enter
        the composition and do not stop unless they are removed
         */
        val transition = rememberInfiniteTransition()
        val translateAnim by transition.animateFloat(
            /**
            Specify animation positions,
            initial Values 0F means it starts from 0 position
             */
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */

                /**
                 * Tween Animates between values over specified [durationMillis]
                 */
                tween(durationMillis = 1200, easing = FastOutSlowInEasing),
                RepeatMode.Reverse
            )
        )

        /**
         * Create a gradient using the list of colors
         * Use Linear Gradient for animating in any direction according to requirement
         * start=specifies the position to start with in cartesian like system
         *       Offset(10f,10f) means x(10,0) , y(0,10)
         * end= Animate the end position to give the shimmer effect using the transition created above
         */
        val brush = Brush.linearGradient(
            colors = ShimmerColorShades,
            start = Offset(10f, 10f),
            end = Offset(translateAnim, translateAnim)
        )

        ShimmerItem(brush = brush, modifier = modifier)
    }

    @Composable
    private fun ShimmerItem(brush: Brush, modifier: Modifier) {

        /**
         * Column composable shaped like a rectangle,
         * set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
         * which will get animated.
         * Add few more Composable to test
         */

        Row {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .height(Dp(150F)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(
                    modifier = modifier
                        .padding(
                            bottom = Dp(8F),
                            start = Dp(8F),
                            end = Dp(8F)
                        )
                        .height(Dp(50F))
                        .fillMaxWidth()
                        .background(brush = brush)
                )

                Spacer(
                    modifier = modifier
                        .height(Dp(50F))
                        .fillMaxWidth()
                        .padding(Dp(8F))
                        .background(brush = brush)
                )
            }

            Spacer(
                modifier = modifier
                    .height(Dp(150F))
                    .width(Dp(150F))
                    .background(brush = brush)
                    .padding(Dp(4f))
                    .clip(RoundedCornerShape(Dp(8F)))
            )

        }
        Spacer(modifier = modifier.height(Dp(8f)))
    }
}

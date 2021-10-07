package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.junemon.compose_stable.R
import com.junemon.compose_stable.core.domain.response.PokemonDetail
import com.junemon.compose_stable.core.presentation.ComposePagerSnapHelper
import com.junemon.compose_stable.util.PokemonConstant
import com.junemon.compose_stable.util.PokemonConstant.ONE_SKILL_MONS
import com.junemon.compose_stable.util.PokemonConstant.ONE_TYPE_MONS
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class ScreensUseCaseImpl @Inject constructor() : ScreensUseCase {
    @Composable
    override fun ListPokemon(
        listOfPokemon: List<PokemonDetail>,
        selectPokemon: (PokemonDetail) -> Unit,
        modifier: Modifier
    ) {
        ComposePagerSnapHelper(
            width = 320.dp, //required
            content = { listState -> //this param is provided by the method itself, add this param below.
                LazyRow(
                    state = listState,
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(Dp(8f))
                ) {
                    items(listOfPokemon) { singlePokemonItem ->
                        val randomName: MutableList<String> =
                            listOfPokemon.shuffled().take(2).map { it.pokemonName }.toMutableList()
                        randomName.add(singlePokemonItem.pokemonName)

                        PokemonItem(
                            singlePokemon = singlePokemonItem,
                            randomName = randomName.shuffled(),
                            pokemonSelect = selectPokemon,
                            modifier = modifier.fillParentMaxWidth()
                        )
                    }
                }
            }
        )

    }

    @Composable
    override fun LottieLoading(loadingSize: Dp) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.Asset(
                PokemonConstant.LOTTIE_LOADING
            )
        )
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
            LottieAnimation(composition, progress, Modifier.size(loadingSize))
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
    private fun PokemonItem(
        singlePokemon: PokemonDetail,
        randomName: List<String>,
        pokemonSelect: (PokemonDetail) -> Unit,
        modifier: Modifier
    ) {
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val transition = updateTransition(targetState = isExpanded, label = "")
        val pokemonImageSizeDp by transition.animateDp(targetValueByState = {
            if (it) 300.dp else 600.dp
        }, label = "")

        val pokemonLogoHeightDp by transition.animateDp(targetValueByState = {
            if (it) 90.dp else 100.dp
        }, label = "")

        val listState = rememberScrollState()

        Card(
            elevation = Dp(4F),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable {
                    pokemonSelect(singlePokemon)
                }
        ) {
            if (isExpanded) {
                Column(
                    modifier
                        .animateContentSize()
                        .padding(Dp(8f))
                        .verticalScroll(listState)
                        .clickable {
                            isExpanded = false
//                            pokemonSelect(singlePokemon)
                        }
                ) {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(),
                        text = singlePokemon.pokemonName,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(singlePokemon.pokemonImage, builder = {
                            crossfade(true)
                        }),
                        modifier = modifier
                            .size(pokemonImageSizeDp)
                            .padding(8.dp),
                        contentDescription = null
                    )

                    Image(
                        painter = painterResource(R.drawable.pokemon_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(pokemonLogoHeightDp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    ListOfPokemonSprite(
                        pokemonItem = singlePokemon,
                        modifier = modifier.fillMaxWidth()
                    )

                    Column(modifier = modifier.fillMaxWidth()) {
                        PokemonDetailStat(pokemonItem = singlePokemon, modifier = modifier)
                        PokemonDetailType(pokemonItem = singlePokemon, modifier = modifier)
                    }
                }

            } else {
                Box(modifier = modifier.fillMaxSize()) {
                    val matrix = ColorMatrix()
                    matrix.setToSaturation(0F)


                    Image(
                        painter = painterResource(R.drawable.pokemon_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(pokemonLogoHeightDp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = rememberImagePainter(singlePokemon.pokemonImage, builder = {
                            crossfade(true)
                        }),
                        modifier = modifier
                            .size(pokemonImageSizeDp)
                            .padding(8.dp),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = null
                    )

                    Column(
                        modifier = modifier
                            .fillMaxHeight()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            modifier = modifier.padding(4.dp),
                            elevation = ButtonDefaults.elevation(8.dp),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                isExpanded = true
                            }) {
                            Text(text = randomName[0])
                        }
                        Button(
                            modifier = modifier.padding(4.dp),
                            elevation = ButtonDefaults.elevation(8.dp),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                isExpanded = true
                            }) {
                            Text(text = randomName[1])
                        }
                        Button(
                            modifier = modifier.padding(4.dp),
                            elevation = ButtonDefaults.elevation(8.dp),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                isExpanded = true
                            }) {
                            Text(text = randomName[2])
                        }
                    }

                }

            }

        }
    }

    @Composable
    private fun ListOfPokemonSprite(pokemonItem: PokemonDetail, modifier: Modifier) {
        Row(modifier = modifier) {
            Image(
                painter = rememberImagePainter(
                    pokemonItem.pokemonSmallImage1,
                    builder = {
                        crossfade(true)
                    }),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null
            )

            Image(
                painter = rememberImagePainter(
                    pokemonItem.pokemonSmallImage2,
                    builder = {
                        crossfade(true)
                    }),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null
            )

            Image(
                painter = rememberImagePainter(
                    pokemonItem.pokemonSmallImage3,
                    builder = {
                        crossfade(true)
                    }),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null
            )

            Image(
                painter = rememberImagePainter(
                    pokemonItem.pokemonSmallImage4,
                    builder = {
                        crossfade(true)
                    }),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null
            )
        }
    }

    @Composable
    private fun PokemonDetailStat(pokemonItem: PokemonDetail, modifier: Modifier) {
        Row {
            Text(
                text = pokemonItem.pokemonStat0.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat0.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row {
            Text(
                text = pokemonItem.pokemonStat1.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat1.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row {
            Text(
                text = pokemonItem.pokemonStat2.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat2.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row {
            Text(
                text = pokemonItem.pokemonStat3.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat3.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        Row {
            Text(
                text = pokemonItem.pokemonStat4.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat4.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }

        Row {
            Text(
                text = pokemonItem.pokemonStat5.name,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat5.point.toString(),
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }


    }

    @Composable
    private fun PokemonDetailType(pokemonItem: PokemonDetail, modifier: Modifier) {
        Row {
            Text(
                text = "Type :",
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonType0, modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        if (pokemonItem.pokemonType1 != ONE_TYPE_MONS) {
            Row {
                Text(
                    text = "",
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonType1,
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        }

        Row {
            Text(
                text = "Ability :",
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonAbility1,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        if (pokemonItem.pokemonAbility2 != ONE_SKILL_MONS) {
            Row {
                Text(
                    text = "",
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonAbility2,
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        }

    }
}

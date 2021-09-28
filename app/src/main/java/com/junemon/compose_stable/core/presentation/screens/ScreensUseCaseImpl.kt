package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
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
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(Dp(8f))
                ) {
                    items(listOfPokemon) { singlePokemonItem ->
                        PokemonItem(
                            singlePokemon = singlePokemonItem,
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
        pokemonSelect: (PokemonDetail) -> Unit,
        modifier: Modifier
    ) {
        val listState = rememberScrollState()
        Card(
            elevation = Dp(4F),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .padding(8.dp)
                .clickable {
                    pokemonSelect(singlePokemon)
                }
        ) {
            Column(
                modifier
                    .clickable {
                        pokemonSelect(singlePokemon)
                    }
                    .padding(Dp(8f))
                    .verticalScroll(listState)
            ) {

                Text(text = singlePokemon.pokemonName, style = MaterialTheme.typography.h5)

                Image(
                    painter = rememberImagePainter(singlePokemon.pokemonImage, builder = {
                        crossfade(true)
                    }),
                    modifier = modifier
                        .size(300.dp)
                        .padding(8.dp),
                    contentDescription = null
                )

                ListOfPokemonSprite(pokemonItem = singlePokemon, modifier = modifier.fillMaxWidth())

                Column(modifier = modifier.fillMaxWidth()) {
                    PokemonDetailStat(pokemonItem = singlePokemon, modifier = modifier)
                    PokemonDetailType(pokemonItem = singlePokemon, modifier = modifier)
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
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonStat0.point.toString(),
                    modifier = modifier.weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row {
                Text(
                    text = pokemonItem.pokemonStat1.name,
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonStat1.point.toString(),
                    modifier = modifier.weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row {
                Text(
                    text = pokemonItem.pokemonStat2.name,
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonStat2.point.toString(),
                    modifier = modifier.weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row {
                Text(
                    text = pokemonItem.pokemonStat3.name,
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonStat3.point.toString(),
                    modifier = modifier.weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        Row {
            Text(
                text = pokemonItem.pokemonStat4.name,
                modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat4.point.toString(),
                modifier = modifier.weight(1f).wrapContentWidth(Alignment.End)
            )
        }

        Row {
            Text(
                text = pokemonItem.pokemonStat5.name,
                modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
            )
            Text(
                text = pokemonItem.pokemonStat5.point.toString(),
                modifier = modifier.weight(1f).wrapContentWidth(Alignment.End)
            )
        }


    }

    @Composable
    private fun PokemonDetailType(pokemonItem: PokemonDetail, modifier: Modifier) {
            Row {
                Text(
                    text = "Type :",
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
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
                        modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
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
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = pokemonItem.pokemonAbility1,
                    modifier = modifier.weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            if (pokemonItem.pokemonAbility2 != ONE_SKILL_MONS) {
                Row {
                    Text(
                        text = "",
                        modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
                    )
                    Text(
                        text = pokemonItem.pokemonAbility2,
                        modifier = modifier.weight(1f)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }

    }
}

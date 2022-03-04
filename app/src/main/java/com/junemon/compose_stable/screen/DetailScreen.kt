package com.junemon.compose_stable.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.rememberImagePainter
import com.junemon.compose_stable.feature.PokemonDetailMviViewModel
import timber.log.Timber

@Composable
fun DetailScreen(
    pokemonDetailVM: PokemonDetailMviViewModel,
    modifier: Modifier = Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    val pokemonStatFlowLifecycle = remember(pokemonDetailVM.statState, lifecycleOwner) {
        pokemonDetailVM.statState.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }

    val pokemonAreaFlowLifecycle = remember(pokemonDetailVM.areaState, lifecycleOwner) {
        pokemonDetailVM.areaState.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val pokemonCharacteristicFlowLifecycle =
        remember(pokemonDetailVM.characteristicState, lifecycleOwner) {
            pokemonDetailVM.characteristicState.flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
        }
    val stat by pokemonStatFlowLifecycle.collectAsState(DetailPokemonStatState.initial())
    val area by pokemonAreaFlowLifecycle.collectAsState(DetailScreenAreaState.initial())
    val characteristic by pokemonCharacteristicFlowLifecycle.collectAsState(
        DetailScreenCharacteristicState.initial()
    )

    val scrollState = rememberScrollState()
    Card(
        elevation = Dp(4F),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {

        Column(
            modifier
                .animateContentSize()
                .padding(8.dp)

        ) {
            when {
                stat.isLoading -> {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        pokemonDetailVM.LottieLoading(loadingSize = 50.dp)
                    }
                }
                stat.data != null -> {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(),
                        text = stat.data!!.pokemonName,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(
                            request = pokemonDetailVM.provideCoilImageRequest(imageUrl = stat.data!!.pokemonImage),
                            imageLoader = pokemonDetailVM.provideCoilImageLoader(),
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp),
                        contentDescription = null
                    )

                    pokemonDetailVM.ListOfPokemonSprite(
                        pokemonItem = stat.data!!
                    )

                    Column(modifier = modifier.fillMaxWidth()) {
                        pokemonDetailVM.PokemonDetailStat(
                            pokemonItem = stat.data!!
                        )
                        pokemonDetailVM.PokemonDetailType(
                            pokemonItem = stat.data!!
                        )
                    }
                }

                stat.failedMessage.isNotEmpty() -> {
                    Timber.e(stat.failedMessage)
                }
            }

            when {
                area.isLoading -> Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pokemonDetailVM.LottieLoading(loadingSize = 50.dp)
                }
                area.data.isNotEmpty() -> {
                    pokemonDetailVM.DetailAreaRow(areas = area.data)
                }

                area.failedMessage.isNotEmpty() -> {
                    Timber.e(stat.failedMessage)
                }
            }

            when {
                characteristic.isLoading -> Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pokemonDetailVM.LottieLoading(loadingSize = 50.dp)
                }
                characteristic.data.isNotEmpty() -> {
                   pokemonDetailVM.DetailCharacteristicRow(characteristic = characteristic.data)

                }

                characteristic.failedMessage.isNotEmpty() -> {
                    Timber.e(stat.failedMessage)
                }
            }

        }

    }


}
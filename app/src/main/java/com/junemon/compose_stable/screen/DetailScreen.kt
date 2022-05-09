package com.junemon.compose_stable.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.junemon.compose_stable.feature.PokemonDetailMviViewModel
import timber.log.Timber

@Composable
fun DetailScreen(
    pokemonDetailVM: PokemonDetailMviViewModel,
    modifier: Modifier = Modifier
) {

    val stat = pokemonDetailVM.uiStatState
    val area = pokemonDetailVM.uiAreaState
    val characteristic = pokemonDetailVM.uiCharacteristicState

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
                stat.isLoading -> Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pokemonDetailVM.LottieLoading(loadingSize = 50.dp)
                }

                stat.data != null -> {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(),
                        text = stat.data.pokemonName,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )

                    AsyncImage(
                        model = pokemonDetailVM.provideCoilImageRequest(imageUrl = stat.data.pokemonImage),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp), contentDescription = null
                    )


                    pokemonDetailVM.ListOfPokemonSprite(
                        pokemonItem = stat.data
                    )

                    Column(modifier = modifier.fillMaxWidth()) {
                        pokemonDetailVM.PokemonDetailStat(
                            pokemonItem = stat.data
                        )
                        pokemonDetailVM.PokemonDetailType(
                            pokemonItem = stat.data
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
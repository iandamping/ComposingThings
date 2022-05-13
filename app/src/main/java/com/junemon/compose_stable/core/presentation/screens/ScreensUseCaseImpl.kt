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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.airbnb.lottie.compose.*
import com.junemon.compose_stable.R
import com.junemon.compose_stable.core.data.datasource.cache.PokemonEntity
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
    override fun provideCoilImageLoader() = LocalContext.current.imageLoader

    @Composable
    override fun provideCoilImageRequest(imageUrl: String): ImageRequest {
        return ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .addHeader("Cache-Control", "max-age=20,public")
            .crossfade(true)
            .build()
    }

    @Composable
    override fun ListPokemon(
        listOfPokemon: List<PokemonEntity>,
        selectPokemon: (PokemonEntity) -> Unit,
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
    override fun PokemonItem(
        singlePokemon: PokemonEntity,
        randomName: List<String>,
        pokemonSelect: (PokemonEntity) -> Unit,
        modifier: Modifier
    ) {
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        val transition = updateTransition(targetState = isExpanded, label = "")
        val pokemonImageSizeDp by transition.animateDp(targetValueByState = {
            if (it) 300.dp else 600.dp
        }, label = "")

        val pokemonLogoHeightDp by transition.animateDp(targetValueByState = {
            if (it) 90.dp else 100.dp
        }, label = "")


        Card(
            elevation = Dp(4F),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable(enabled = isExpanded) {

                }
        ) {
            if (isExpanded) {
                Column(
                    modifier
                        .animateContentSize()
                        .padding(8.dp)
                        .clickable {
                            pokemonSelect(singlePokemon)
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
                    AsyncImage(
                        model =
                        provideCoilImageRequest(imageUrl = singlePokemon.pokemonImage),
                        imageLoader = provideCoilImageLoader(),
                        contentDescription = null,
                        modifier = modifier
                            .size(pokemonImageSizeDp)
                            .padding(8.dp)
                    )


                    Image(
                        painter = painterResource(R.drawable.pokemon_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(pokemonLogoHeightDp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

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
                    AsyncImage(
                        model = provideCoilImageRequest(imageUrl = singlePokemon.pokemonImage),
                        contentDescription = null,
                        imageLoader = provideCoilImageLoader(),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        modifier = modifier
                            .size(pokemonImageSizeDp)
                            .padding(8.dp)
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
                                isExpanded = !isExpanded
                            }) {
                            Text(text = randomName[0])
                        }
                        Button(
                            modifier = modifier.padding(4.dp),
                            elevation = ButtonDefaults.elevation(8.dp),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                isExpanded = !isExpanded
                            }) {
                            Text(text = randomName[1])
                        }
                        Button(
                            modifier = modifier.padding(4.dp),
                            elevation = ButtonDefaults.elevation(8.dp),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                isExpanded = !isExpanded
                            }) {
                            Text(text = randomName[2])
                        }
                    }

                }

            }

        }
    }

    @Composable
    override fun DetailPokemonItem(
        singlePokemon: PokemonDetail,
        pokemonSelect: (PokemonDetail) -> Unit,
        modifier: Modifier
    ) {
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
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .animateContentSize(),
                    text = singlePokemon.pokemonName,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                AsyncImage(
                    model = provideCoilImageRequest(imageUrl = singlePokemon.pokemonImage),
                    imageLoader = provideCoilImageLoader(),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp), contentDescription = null
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

        }

    }

    @Composable
    override fun ListOfPokemonSprite(pokemonItem: PokemonDetail, modifier: Modifier) {
        Row(modifier = modifier) {
            AsyncImage(
                model = provideCoilImageRequest(imageUrl = pokemonItem.pokemonSmallImage1),
                imageLoader = provideCoilImageLoader(),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null
            )

            AsyncImage(
                model = provideCoilImageRequest(imageUrl = pokemonItem.pokemonSmallImage3), contentDescription = null,
                imageLoader = provideCoilImageLoader(),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f)
            )

            AsyncImage(model = provideCoilImageRequest(imageUrl = pokemonItem.pokemonSmallImage4)
                , imageLoader = provideCoilImageLoader(),
                modifier = modifier
                    .size(100.dp)
                    .weight(1f),
                contentDescription = null)
        }
    }

    @Composable
    override fun DetailStatRow(firstText: String, secondText: String, modifier: Modifier) {
        Row {
            Text(
                text = firstText,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = secondText,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }

    @Composable
    override fun PokemonDetailStat(pokemonItem: PokemonDetail, modifier: Modifier) {
        DetailStatRow(
            firstText = pokemonItem.pokemonStat0.name,
            secondText = pokemonItem.pokemonStat0.point.toString(),
            modifier = modifier
        )
        DetailStatRow(
            firstText = pokemonItem.pokemonStat1.name,
            secondText = pokemonItem.pokemonStat1.point.toString(),
            modifier = modifier
        )
        DetailStatRow(
            firstText = pokemonItem.pokemonStat2.name,
            secondText = pokemonItem.pokemonStat2.point.toString(),
            modifier = modifier
        )
        DetailStatRow(
            firstText = pokemonItem.pokemonStat3.name,
            secondText = pokemonItem.pokemonStat3.point.toString(),
            modifier = modifier
        )
        DetailStatRow(
            firstText = pokemonItem.pokemonStat4.name,
            secondText = pokemonItem.pokemonStat4.point.toString(),
            modifier = modifier
        )
        DetailStatRow(
            firstText = pokemonItem.pokemonStat5.name,
            secondText = pokemonItem.pokemonStat5.point.toString(),
            modifier = modifier
        )
    }

    @Composable
    override fun DetailAreaRow(modifier: Modifier, areas: List<String>) {
        if (areas.size < 2) {
            Row {
                Text(
                    text = "Area :",
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = areas[0], modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        } else {
            areas.forEachIndexed { index, s ->
                Row {
                    Text(
                        text = if (index == 0) "Area : " else "",
                        modifier = modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                    )

                    Text(
                        text = s, modifier = modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }
        }


    }

    @Composable
    override fun DetailCharacteristicRow(modifier: Modifier, characteristic: String) {
        Row {
            Text(
                text = "Characteristic",
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = characteristic, modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }

    @Composable
    override fun DetailTypeRow(
        firstText: String,
        secondText: String,
        thirdText: String,
        modifier: Modifier
    ) {
        Row {
            Text(
                text = firstText,
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = secondText, modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
        if (thirdText != ONE_TYPE_MONS && thirdText != ONE_SKILL_MONS) {
            Row {
                Text(
                    text = "",
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = thirdText, modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        }

    }

    @Composable
    override fun PokemonDetailType(pokemonItem: PokemonDetail, modifier: Modifier) {
        DetailTypeRow(
            firstText = "Type :",
            secondText = pokemonItem.pokemonType0,
            thirdText = pokemonItem.pokemonType1,
            modifier = modifier
        )

        DetailTypeRow(
            firstText = "Ability :",
            secondText = pokemonItem.pokemonAbility1,
            thirdText = pokemonItem.pokemonAbility2,
            modifier = modifier
        )

    }
}

package com.junemon.compose_stable.core.presentation.screens

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.request.ImageRequest
import com.junemon.compose_stable.core.data.datasource.cache.PokemonEntity
import com.junemon.compose_stable.core.domain.response.PokemonDetail

/**
 * Created by Ian Damping on 08,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ScreensUseCase {

    @Composable
    fun provideCoilImageLoader():ImageLoader

    @Composable
    fun provideCoilImageRequest(imageUrl: String): ImageRequest

    @Composable
    fun ListPokemon(listOfPokemon: List<PokemonEntity>, selectPokemon: (PokemonEntity) -> Unit, modifier: Modifier)

    @Composable
    fun HorizontalPokemonItem(
        singlePokemon: PokemonEntity,
        randomName: List<String>,
        pokemonSelect: (PokemonEntity) -> Unit,
        modifier: Modifier
    )

    @Composable
    fun VerticalPokemonItem(
        singlePokemon: PokemonEntity,
        randomName: List<String>,
        pokemonSelect: (PokemonEntity) -> Unit,
        modifier: Modifier
    )

    @Composable
    fun DetailPokemonItem(
        singlePokemon: PokemonDetail,
        pokemonSelect: (PokemonDetail) -> Unit,
        modifier: Modifier
    )

    @Composable
    fun ListOfPokemonSprite(pokemonItem: PokemonDetail, modifier: Modifier)

    @Composable
    fun DetailStatRow(firstText: String, secondText: String, modifier: Modifier)

    @Composable
    fun PokemonDetailStat(pokemonItem: PokemonDetail, modifier: Modifier)

    @Composable
    fun DetailTypeRow(firstText: String, secondText: String, thirdText:String,modifier: Modifier)

    @Composable
    fun PokemonDetailType(pokemonItem: PokemonDetail, modifier: Modifier)

    @Composable
    fun DetailCharacteristicRow(modifier: Modifier , characteristic: String)

    @Composable
    fun DetailAreaRow(modifier: Modifier , areas: List<String>)

    @Composable
    fun LottieLoading(loadingSize:Dp)

    @Composable
    fun BackHandler(
        backDispatcher: OnBackPressedDispatcher,
        enabled: Boolean,
        onBack: () -> Unit
    )
}
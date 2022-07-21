package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.DummyPokemon.DUMMY_LIST_POKEMON_AREA
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_CHARACTERISTIC
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_DETAIL
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_MAIN_RESPONSE
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_SPECIES_DETAIL
import com.junemon.compose_stable.DummyPokemon.DUMMY_URL_POKEMON_RESULTS_1
import com.junemon.compose_stable.DummyPokemon.DUMMY_URL_POKEMON_RESULTS_2
import com.junemon.compose_stable.DummyPokemon.DUMMY_URL_POKEMON_RESULTS_3
import com.junemon.compose_stable.core.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.core.datasource.PokemonRemoteDataSourceImpl
import com.junemon.compose_stable.core.datasource.network.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.datasource.network.PokemonApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRemoteDataSourceImplTest {

    private val api: PokemonApi = mockk()
    lateinit var sut: PokemonRemoteDataSource

    @Before
    fun setUp() {
        sut = PokemonRemoteDataSourceImpl(api)
    }

    @Test
    fun `getPokemon successfully return value`() = runTest {
        //given
        coEvery { api.getMainPokemon() } returns DUMMY_POKEMON_MAIN_RESPONSE
        //when
        val results = sut.getPokemon()
        //then
        coVerify { api.getMainPokemon() }
        Assert.assertEquals(DUMMY_POKEMON_MAIN_RESPONSE.pokemonResults, results)
        Assert.assertEquals(DUMMY_URL_POKEMON_RESULTS_1, results[0])
        Assert.assertEquals(DUMMY_URL_POKEMON_RESULTS_2, results[1])
        Assert.assertEquals(DUMMY_URL_POKEMON_RESULTS_3, results[2])
    }

    @Test
    fun `getPokemon failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getMainPokemon() } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getPokemon()
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getMainPokemon() }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailPokemon successfully return value`() = runTest {
        //given
        coEvery { api.getPokemonDetailByUrl("a") } returns DUMMY_POKEMON_DETAIL
        //when
        val results = sut.getDetailPokemonByUrl("a")
        //then
        coVerify { api.getPokemonDetailByUrl(any()) }
        Assert.assertEquals(DUMMY_POKEMON_DETAIL, results)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonId, results.pokemonId)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonName, results.pokemonName)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonHeight, results.pokemonHeight)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonWeight, results.pokemonWeight)
    }

    @Test
    fun `getDetailPokemon failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonDetailByUrl("a") } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getDetailPokemonByUrl("a")
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonDetailByUrl(any()) }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailPokemonCharacteristic successfully return value`() = runTest {
        //given
        coEvery { api.getPokemonCharacteristic(1) } returns DUMMY_POKEMON_CHARACTERISTIC
        //when
        val results = sut.getDetailPokemonCharacteristic(1)
        //then
        coVerify { api.getPokemonCharacteristic(any()) }
        Assert.assertEquals(DUMMY_POKEMON_CHARACTERISTIC.descriptions[0].description, results)

    }

    @Test
    fun `getDetailPokemonCharacteristic failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonCharacteristic(1) } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getDetailPokemonCharacteristic(1)
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonCharacteristic(any()) }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getPokemonLocationAreas successfully return value`() = runTest {
        //given
        coEvery { api.getPokemonLocationAreas(1) } returns DUMMY_LIST_POKEMON_AREA
        //when
        val results = sut.getPokemonLocationAreas(1)
        //then
        coVerify { api.getPokemonLocationAreas(any()) }
        Assert.assertEquals(DUMMY_LIST_POKEMON_AREA[0].area.name, results[0])
        Assert.assertEquals(DUMMY_LIST_POKEMON_AREA[1].area.name, results[1])
        Assert.assertEquals(DUMMY_LIST_POKEMON_AREA[2].area.name, results[2])

    }

    @Test
    fun `getPokemonLocationAreas failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonLocationAreas(1) } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getPokemonLocationAreas(1)
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonLocationAreas(any()) }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailPokemonById successfully return value`() = runTest {
        //given
        coEvery { api.getPokemonById(1) } returns DUMMY_POKEMON_DETAIL
        //when
        val results = sut.getDetailPokemonById(1)
        //then
        coVerify { api.getPokemonById(any()) }
        Assert.assertEquals(DUMMY_POKEMON_DETAIL, results)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonId, results.pokemonId)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonName, results.pokemonName)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonHeight, results.pokemonHeight)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonWeight, results.pokemonWeight)

    }

    @Test
    fun `getDetailPokemonById failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonById(1) } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getDetailPokemonById(1)
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonById(any()) }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailSpeciesPokemon successfully return value`() = runTest {
        //given
        coEvery { api.getPokemonSpecies("a") } returns DUMMY_POKEMON_SPECIES_DETAIL
        //when
        val results = sut.getDetailSpeciesPokemon("a")
        //then
        coVerify { api.getPokemonSpecies(any()) }
        Assert.assertEquals(DUMMY_POKEMON_SPECIES_DETAIL, results)
        Assert.assertEquals(
            DUMMY_POKEMON_SPECIES_DETAIL.pokemonCaptureRate,
            results.pokemonCaptureRate
        )
        Assert.assertEquals(DUMMY_POKEMON_SPECIES_DETAIL.pokemonHappines, results.pokemonHappines)
        Assert.assertEquals(
            DUMMY_POKEMON_SPECIES_DETAIL.pokemonColor.pokemonColor,
            results.pokemonColor.pokemonColor
        )
        Assert.assertEquals(
            DUMMY_POKEMON_SPECIES_DETAIL.pokemonShape.pokemonShape,
            results.pokemonShape.pokemonShape
        )
        Assert.assertEquals(
            DUMMY_POKEMON_SPECIES_DETAIL.pokemonGeneration.pokemonGenerationLString,
            results.pokemonGeneration.pokemonGenerationLString
        )

    }

    @Test
    fun `getDetailSpeciesPokemon failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonSpecies("a") } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getDetailSpeciesPokemon("a")
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonSpecies(any()) }
        Assert.assertTrue(exceptionThrown)
    }
}
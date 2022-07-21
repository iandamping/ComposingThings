package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_DETAIL
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_MAIN_RESPONSE
import com.junemon.compose_stable.DummyPokemon.DUMMY_POKEMON_SPECIES_DETAIL
import com.junemon.compose_stable.core.datasource.PokemonRemoteDataSource
import com.junemon.compose_stable.core.datasource.network.NetworkConstant
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.domain.repository.PokemonRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {

    lateinit var sut: PokemonRepository
    private val dataSource: PokemonRemoteDataSource = mockk()

    @Before
    fun setUp() {
        sut = PokemonRepositoryImpl(dataSource)
    }

    @Test
    fun `getPokemon successfully return value`() = runTest {
        //given
        coEvery { dataSource.getPokemon() } returns DUMMY_POKEMON_MAIN_RESPONSE.pokemonResults
        coEvery { dataSource.getDetailPokemonByUrl("a") } returns DUMMY_POKEMON_DETAIL
        //when
        val results = sut.getPokemon() as Results.Success
        //then
        coVerify { dataSource.getPokemon() }
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonId, results.data[0].pokemonId)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonName, results.data[0].pokemonName)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonHeight, results.data[0].pokemonHeight)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonWeight, results.data[0].pokemonWeight)
    }

    @Test
    fun `getPokemon failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getPokemon() } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getPokemon() as Results.Error
        //then
        coVerify { dataSource.getPokemon() }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `getDetailSpeciesPokemon successfully return value`() = runTest {
        //given
        coEvery { dataSource.getDetailSpeciesPokemon("a") } returns DUMMY_POKEMON_SPECIES_DETAIL
        //when
        val results = sut.getDetailSpeciesPokemon("a") as Results.Success
        //then
        coVerify { dataSource.getDetailSpeciesPokemon(any()) }
        assertEquals(DUMMY_POKEMON_SPECIES_DETAIL.toDomain().captureRate, results.data.captureRate)
        assertEquals(DUMMY_POKEMON_SPECIES_DETAIL.toDomain().color, results.data.color)
        assertEquals(DUMMY_POKEMON_SPECIES_DETAIL.toDomain().eggGroup1, results.data.eggGroup1)
        assertEquals(DUMMY_POKEMON_SPECIES_DETAIL.toDomain().eggGroup2, results.data.eggGroup2)
    }

    @Test
    fun `getDetailSpeciesPokemon failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getDetailSpeciesPokemon("a") } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getDetailSpeciesPokemon("a") as Results.Error
        //then
        coVerify { dataSource.getDetailSpeciesPokemon(any()) }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `getDetailPokemonCharacteristic successfully return value`() = runTest {
        //given
        coEvery { dataSource.getDetailPokemonCharacteristic(1) } returns "a"
        //when
        val results = sut.getDetailPokemonCharacteristic(1) as Results.Success
        //then
        coVerify { dataSource.getDetailPokemonCharacteristic(any()) }
        assertEquals("a", results.data)
    }

    @Test
    fun `getDetailPokemonCharacteristic failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getDetailPokemonCharacteristic(1) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getDetailPokemonCharacteristic(1) as Results.Error
        //then
        coVerify { dataSource.getDetailPokemonCharacteristic(any()) }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `getPokemonLocationAreas successfully return value`() = runTest {
        //given
        coEvery { dataSource.getPokemonLocationAreas(1) } returns listOf("a", "a", "a")
        //when
        val results = sut.getPokemonLocationAreas(1) as Results.Success
        //then
        coVerify { dataSource.getPokemonLocationAreas(any()) }
        assertEquals(listOf("a", "a", "a"), results.data)
    }

    @Test
    fun `getPokemonLocationAreas failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getPokemonLocationAreas(1) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getPokemonLocationAreas(1) as Results.Error
        //then
        coVerify { dataSource.getPokemonLocationAreas(any()) }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `getPokemonById successfully return value`() = runTest {
        //given
        coEvery { dataSource.getDetailPokemonById(1) } returns DUMMY_POKEMON_DETAIL
        //when
        val results = sut.getPokemonById(1) as Results.Success
        //then
        coVerify { dataSource.getDetailPokemonById(any()) }
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain(), results.data)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonId, results.data.pokemonId)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonWeight, results.data.pokemonWeight)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonHeight, results.data.pokemonHeight)
        assertEquals(DUMMY_POKEMON_DETAIL.toDomain().pokemonName, results.data.pokemonName)
    }

    @Test
    fun `getPokemonById failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getDetailPokemonById(1) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getPokemonById(1) as Results.Error
        //then
        coVerify { dataSource.getDetailPokemonById(any()) }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }
}
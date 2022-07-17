package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.Dummy.DUMMY_POKEMON_DETAIL
import com.junemon.compose_stable.Dummy.DUMMY_POKEMON_MAIN_RESPONSE
import com.junemon.compose_stable.Dummy.DUMMY_URL_POKEMON_RESULTS_1
import com.junemon.compose_stable.Dummy.DUMMY_URL_POKEMON_RESULTS_2
import com.junemon.compose_stable.Dummy.DUMMY_URL_POKEMON_RESULTS_3
import com.junemon.compose_stable.network.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.network.PokemonApi
import io.mockk.*
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
    fun `getPokemon successfully return value`()= runTest{
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
    fun `getPokemon failed and Throw exception`()= runTest{
        //given
        var exceptionThrown = false
        coEvery { api.getMainPokemon() } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getPokemon()
        } catch(exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getMainPokemon() }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailPokemon successfully return value`()= runTest{
        //given
        coEvery { api.getPokemonDetailByUrl("a") } returns DUMMY_POKEMON_DETAIL
        //when
        val results = sut.getDetailPokemonByUrl("a")
        //then
        coVerify { api.getPokemonDetailByUrl(any()) }
        Assert.assertEquals(DUMMY_POKEMON_DETAIL, results)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonId, 1)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonName, "a")
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonHeight, 1)
        Assert.assertEquals(DUMMY_POKEMON_DETAIL.pokemonWeight, 1)
    }

    @Test
    fun `getDetailPokemon failed and Throw exception`()= runTest{
        //given
        var exceptionThrown = false
        coEvery { api.getPokemonDetailByUrl("a") } throws Exception(NETWORK_ERROR)
        //when
        try {
            sut.getDetailPokemonByUrl("a")
        } catch(exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPokemonDetailByUrl(any()) }
        Assert.assertTrue(exceptionThrown)
    }
}
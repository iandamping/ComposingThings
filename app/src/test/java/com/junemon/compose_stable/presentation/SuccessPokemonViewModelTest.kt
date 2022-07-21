package com.junemon.compose_stable.presentation

import com.junemon.compose_stable.DummyMovies
import com.junemon.compose_stable.DummyPokemon
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.repository.MovieRepository
import com.junemon.compose_stable.domain.repository.PokemonRepository
import com.junemon.compose_stable.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SuccessPokemonViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    lateinit var sut: PokemonViewModel
    lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        repository = mockk{
            coEvery { this@mockk.getPokemon() } returns Results.Success(listOf(DummyPokemon.DUMMY_POKEMON_DETAIL.toDomain()))
            coEvery { this@mockk.getPokemonById(any()) } returns Results.Success(DummyPokemon.DUMMY_POKEMON_DETAIL.toDomain())
            coEvery { this@mockk.getPokemonLocationAreas(any()) } returns Results.Success(listOf("a","b","c"))
            coEvery { this@mockk.getDetailPokemonCharacteristic(any()) } returns Results.Success("a")
        }
        sut = PokemonViewModel(repository)
    }

    @Test
    fun `asserting value of selectedPokemonId`() = runTest {
        assertEquals(null, sut.selectedPokemonId.value) // assert initial value

        sut.setSelectedPokemonId(2345)
        assertEquals(2345, sut.selectedPokemonId.value) // assert initial value

    }

    @Test
    fun `getPokemon return success inside init viewModel`() = runTest{
        assertEquals(listOf(DummyPokemon.DUMMY_POKEMON_DETAIL.toDomain()), sut.uiPokemonState.data)
        assertEquals(false, sut.uiPokemonState.isLoading)
        assertEquals("", sut.uiPokemonState.failedMessage)
    }

    @Test
    fun `getPokemonById return success inside init viewModel`() = runTest{
        sut.setSelectedPokemonId(1)

        assertEquals(DummyPokemon.DUMMY_POKEMON_DETAIL.toDomain(), sut.uiPokemonDetailStatState.data)
        assertEquals(false, sut.uiPokemonDetailStatState.isLoading)
        assertEquals("", sut.uiPokemonDetailStatState.failedMessage)
    }

    @Test
    fun `getPokemonLocationAreas return success inside init viewModel`() = runTest{
        sut.setSelectedPokemonId(1)

        assertEquals(listOf("a","b","c"), sut.uiAreaState.data)
        assertEquals(false, sut.uiAreaState.isLoading)
        assertEquals("", sut.uiAreaState.failedMessage)
    }

    @Test
    fun `getDetailPokemonCharacteristic return success inside init viewModel`() = runTest{
        sut.setSelectedPokemonId(1)

        assertEquals("a", sut.uiCharacteristicState.data)
        assertEquals(false, sut.uiCharacteristicState.isLoading)
        assertEquals("", sut.uiCharacteristicState.failedMessage)
    }
}
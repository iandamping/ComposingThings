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
        }
        sut = PokemonViewModel(repository)
    }

    @Test
    fun `getPokemon return success inside init viewModel`() = runTest{
        assertEquals(listOf(DummyPokemon.DUMMY_POKEMON_DETAIL.toDomain()), sut.uiPokemonState.data)
        assertEquals(false, sut.uiPokemonState.isLoading)
        assertEquals("", sut.uiPokemonState.failedMessage)
    }

}
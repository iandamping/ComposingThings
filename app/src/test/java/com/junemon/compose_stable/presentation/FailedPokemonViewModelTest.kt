package com.junemon.compose_stable.presentation

import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.model.pokemon.PokemonDetail
import com.junemon.compose_stable.domain.repository.PokemonRepository
import com.junemon.compose_stable.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FailedPokemonViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    lateinit var sut: PokemonViewModel
    lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        repository = mockk {
            coEvery { this@mockk.getPokemon() } returns Results.Error(NetworkConstant.NETWORK_ERROR)
            coEvery { this@mockk.getPokemonById(any()) } returns Results.Error(NetworkConstant.NETWORK_ERROR)
            coEvery { this@mockk.getPokemonLocationAreas(any()) } returns Results.Error(
                NetworkConstant.NETWORK_ERROR
            )
            coEvery { this@mockk.getDetailPokemonCharacteristic(any()) } returns Results.Error(
                NetworkConstant.NETWORK_ERROR
            )
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
    fun `getPokemon return error inside init viewModel`() = runTest {
        assertEquals(emptyList<PokemonDetail>(), sut.uiPokemonState.data)
        assertEquals(false, sut.uiPokemonState.isLoading)
        assertEquals(NetworkConstant.NETWORK_ERROR, sut.uiPokemonState.failedMessage)
    }

    @Test
    fun `getPokemonById return error inside init viewModel`() = runTest {
        sut.setSelectedPokemonId(1)

        assertEquals(null, sut.uiPokemonDetailStatState.data)
        assertEquals(false, sut.uiPokemonDetailStatState.isLoading)
        assertEquals(NetworkConstant.NETWORK_ERROR, sut.uiPokemonDetailStatState.failedMessage)
    }

    @Test
    fun `getPokemonLocationAreas return error inside init viewModel`() = runTest {
        sut.setSelectedPokemonId(1)

        assertEquals(emptyList<String>(), sut.uiAreaState.data)
        assertEquals(false, sut.uiAreaState.isLoading)
        assertEquals(NetworkConstant.NETWORK_ERROR, sut.uiAreaState.failedMessage)
    }

    @Test
    fun `getDetailPokemonCharacteristic return error inside init viewModel`() = runTest {
        sut.setSelectedPokemonId(1)

        assertEquals(null, sut.uiCharacteristicState.data)
        assertEquals(false, sut.uiCharacteristicState.isLoading)
        assertEquals(NetworkConstant.NETWORK_ERROR, sut.uiCharacteristicState.failedMessage)
    }
}
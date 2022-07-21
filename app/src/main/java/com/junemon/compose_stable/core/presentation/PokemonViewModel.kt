package com.junemon.compose_stable.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.pokemon.PokemonDetail
import com.junemon.compose_stable.core.domain.repository.PokemonRepository
import com.junemon.compose_stable.core.presentation.state.PokemonDetailCharacteristicState
import com.junemon.compose_stable.core.presentation.state.PokemonDetailLocationState
import com.junemon.compose_stable.core.presentation.state.PokemonDetailState
import com.junemon.compose_stable.core.presentation.state.PokemonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    private val _selectedPokemonId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val selectedPokemonId: StateFlow<Int?> = _selectedPokemonId.asStateFlow()

    var uiAreaState by mutableStateOf(PokemonDetailLocationState.initial())
        private set

    var uiCharacteristicState by mutableStateOf(PokemonDetailCharacteristicState.initial())
        private set

    var uiPokemonDetailStatState by mutableStateOf(PokemonDetailState.initial())
        private set

    var uiPokemonState by mutableStateOf(PokemonState.initial())
        private set

    fun setSelectedPokemonId(id: Int) {
        _selectedPokemonId.value = id
    }

    init {

        viewModelScope.launch {
            uiPokemonState = when (val results = repository.getPokemon()) {
                is Results.Error -> uiPokemonState.copy(
                    isLoading = false,
                    failedMessage = results.errorMessage
                )
                is Results.Success -> uiPokemonState.copy(
                    isLoading = false,
                    data = results.data
                )
            }
        }


        viewModelScope.launch {
            selectedPokemonId.collect { id ->
                if (id != null) {
                    val job1 = viewModelScope.launch {
                        consumePokemonDetailById(repository.getPokemonById(id))
                    }
                    val job2 = viewModelScope.launch {
                        consumePokemonAreaById(repository.getPokemonLocationAreas(id))
                    }
                    val job3 = viewModelScope.launch {
                        consumeDetailPokemonCharacteristicById(
                            repository.getDetailPokemonCharacteristic(
                                id
                            )
                        )
                    }
                    listOf(job1, job2, job3).joinAll()
                }
            }
        }


    }


    private fun consumePokemonAreaById(data: Results<List<String>>) {
        uiAreaState = when (data) {
            is Results.Success ->
                uiAreaState.copy(isLoading = false, data = data.data)
            is Results.Error ->
                uiAreaState.copy(isLoading = false, failedMessage = data.errorMessage)
        }
    }

    private fun consumeDetailPokemonCharacteristicById(data: Results<String>) {
        uiCharacteristicState = when (data) {
            is Results.Success ->
                uiCharacteristicState.copy(isLoading = false, data = data.data)

            is Results.Error ->
                uiCharacteristicState.copy(isLoading = false, failedMessage = data.errorMessage)
        }
    }

    private fun consumePokemonDetailById(data: Results<PokemonDetail>) {
        uiPokemonDetailStatState = when (data) {
            is Results.Success ->
                uiPokemonDetailStatState.copy(isLoading = false, data = data.data)
            is Results.Error ->
                uiPokemonDetailStatState.copy(isLoading = false, failedMessage = data.errorMessage)

        }
    }
}
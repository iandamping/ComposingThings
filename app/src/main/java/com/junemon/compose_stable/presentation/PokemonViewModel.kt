package com.junemon.compose_stable.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.repository.PokemonRepository
import com.junemon.compose_stable.presentation.state.PokemonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    var uiPokemonState by mutableStateOf(PokemonState.initial())
        private set


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
    }
}
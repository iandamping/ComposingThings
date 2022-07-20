package com.junemon.compose_stable.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.repository.MovieRepository
import com.junemon.compose_stable.presentation.state.movie.DetailMovieState
import com.junemon.compose_stable.presentation.state.movie.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    var uiMovieState by mutableStateOf(MovieState.initial())
        private set

    var uiDetailMovieState by mutableStateOf(DetailMovieState.initial())
        private set

    private val _movieId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val movieId: StateFlow<Int?> = _movieId.asStateFlow()

    init {
        viewModelScope.launch {
            uiMovieState = when (val results = repository.getMovie()) {
                is Results.Error -> uiMovieState.copy(
                    isLoading = false,
                    failedMessage = results.errorMessage
                )
                is Results.Success -> uiMovieState.copy(
                    isLoading = false,
                    data = results.data
                )
            }
        }

        viewModelScope.launch {
            movieId.collect { id ->
                if (id != null) {
                    uiDetailMovieState = when (val results = repository.getDetailMovie(id)) {
                        is Results.Error -> uiDetailMovieState.copy(
                            isLoading = false,
                            failedMessage = results.errorMessage
                        )
                        is Results.Success -> uiDetailMovieState.copy(
                            isLoading = false,
                            data = results.data
                        )
                    }
                }
            }
        }
    }

    fun setMovieId(data: Int) {
        _movieId.value = data
    }
}
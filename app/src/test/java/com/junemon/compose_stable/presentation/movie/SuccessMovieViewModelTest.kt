package com.junemon.compose_stable.presentation.movie

import com.junemon.compose_stable.DummyMovies
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.repository.MovieRepository
import com.junemon.compose_stable.presentation.MovieViewModel
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
class SuccessMovieViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    lateinit var sut: MovieViewModel
    lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        repository = mockk{
            coEvery { this@mockk.getMovie() } returns Results.Success(DummyMovies.DUMMY_LIST_MOVIE.map { it.toDomain() })
            coEvery { this@mockk.getDetailMovie(any()) } returns Results.Success(DummyMovies.DUMMY_DETAIL_MOVIE.toDomain())
        }
        sut = MovieViewModel(repository)
    }

    @Test
    fun `asserting value of movieId`() = runTest {
        assertEquals(null, sut.movieId.value) // assert initial value

        sut.setMovieId(2345)
        assertEquals(2345, sut.movieId.value) // assert initial value

    }

    @Test
    fun `getMovie return success inside init viewModel`() = runTest{
        assertEquals(DummyMovies.DUMMY_LIST_MOVIE.map { it.toDomain() }, sut.uiMovieState.data)
        assertEquals(false, sut.uiMovieState.isLoading)
        assertEquals("", sut.uiMovieState.failedMessage)
    }



    @Test
    fun `getDetailMovie return success inside init viewModel using userQuery value`() = runTest {
        sut.setMovieId(2345)

        assertEquals(DummyMovies.DUMMY_DETAIL_MOVIE.toDomain(), sut.uiMovieDetailState.data) // assert state value
        assertEquals(false, sut.uiMovieDetailState.isLoading) // assert state value
        assertEquals("", sut.uiMovieDetailState.failedMessage) // assert state value

    }
}
package com.junemon.compose_stable.presentation.movie

import com.junemon.compose_stable.core.datasource.network.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.news.News
import com.junemon.compose_stable.core.domain.repository.MovieRepository
import com.junemon.compose_stable.core.presentation.MovieViewModel
import com.junemon.compose_stable.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FailedMovieViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    lateinit var sut: MovieViewModel
    lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        repository = mockk {
            coEvery { this@mockk.getMovie() } returns Results.Error(NETWORK_ERROR)
            coEvery { this@mockk.getDetailMovie(any()) } returns Results.Error(NETWORK_ERROR)
        }
        sut = MovieViewModel(repository)
    }

    @Test
    fun `asserting value of movieId`() = runTest {
        Assert.assertEquals(null, sut.movieId.value) // assert initial value

        sut.setMovieId(2345)
        Assert.assertEquals(2345, sut.movieId.value) // assert initial value

    }

    @Test
    fun `getMovie return error inside init viewModel`() = runTest {
        Assert.assertEquals(emptyList<News>(), sut.uiMovieState.data)
        Assert.assertEquals(false, sut.uiMovieState.isLoading)
        Assert.assertEquals(NETWORK_ERROR, sut.uiMovieState.failedMessage)
    }


    @Test
    fun `getDetailMovie return error inside init viewModel using userQuery value`() = runTest {
        sut.setMovieId(2345)

        Assert.assertEquals(null, sut.uiMovieDetailState.data) // assert state value
        Assert.assertEquals(false, sut.uiMovieDetailState.isLoading) // assert state value
        Assert.assertEquals(
            NETWORK_ERROR,
            sut.uiMovieDetailState.failedMessage
        ) // assert state value

    }
}
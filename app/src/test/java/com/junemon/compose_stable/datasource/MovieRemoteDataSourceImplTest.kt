package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.DummyMovies.DUMMY_DETAIL_MOVIE
import com.junemon.compose_stable.DummyMovies.DUMMY_LIST_MOVIE
import com.junemon.compose_stable.datasource.network.MovieApi
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.response.movie.MovieMainResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    lateinit var sut: MovieRemoteDataSource

    private val api: MovieApi = mockk()

    @Before
    fun setUp() {
        sut = MovieRemoteDataSourceImpl(api)
    }

    @Test
    fun `getMovie successfully return value`() = runTest {
        //given
        coEvery { api.getPopularMovie() } returns MovieMainResponse(DUMMY_LIST_MOVIE)
        //when
        val results = sut.getMovie()
        //then
        coVerify { api.getPopularMovie() }
        assertEquals(DUMMY_LIST_MOVIE, results)
        assertEquals(DUMMY_LIST_MOVIE[0].id, results[0].id)
        assertEquals(DUMMY_LIST_MOVIE[1].id, results[1].id)
        assertEquals(DUMMY_LIST_MOVIE[2].id, results[2].id)
    }

    @Test
    fun `getMovie failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getPopularMovie() } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        try {
            sut.getMovie()
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            assertEquals(exception.message, NetworkConstant.NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getPopularMovie() }
        assertTrue(exceptionThrown)
    }

    @Test
    fun `getDetailMovie successfully return value`() = runTest {
        //given
        coEvery { api.getDetailMovie(1) } returns DUMMY_DETAIL_MOVIE
        //when
        val results = sut.getDetailMovie(1)
        //then
        coVerify { api.getDetailMovie(1) }
        assertEquals(DUMMY_DETAIL_MOVIE, results)
        assertEquals(DUMMY_DETAIL_MOVIE.id, results.id)
        assertEquals(DUMMY_DETAIL_MOVIE.budget, results.budget)
        assertEquals(DUMMY_DETAIL_MOVIE.runtime, results.runtime)
    }

    @Test
    fun `getDetailMovie failed and Throw exception`() = runTest {
        //given
        var exceptionThrown = false
        coEvery { api.getDetailMovie(1) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        try {
            sut.getDetailMovie(1)
        } catch (exception: Exception) {
            // Maybe put some assertions on the exception here.
            assertEquals(exception.message, NetworkConstant.NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getDetailMovie(movieId = any()) }
        assertTrue(exceptionThrown)
    }
}
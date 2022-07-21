package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.DummyMovies.DUMMY_DETAIL_MOVIE
import com.junemon.compose_stable.DummyMovies.DUMMY_LIST_MOVIE
import com.junemon.compose_stable.core.datasource.MovieRemoteDataSource
import com.junemon.compose_stable.core.datasource.network.NetworkConstant
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.repository.MovieRepository
import com.junemon.compose_stable.core.domain.repository.MovieRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    lateinit var sut: MovieRepository

    private val dataSource: MovieRemoteDataSource = mockk()

    @Before
    fun setUp() {
        sut = MovieRepositoryImpl(dataSource)
    }

    @Test
    fun `getMovie successfully return value`() = runTest {
        //given
        coEvery { dataSource.getMovie() } returns DUMMY_LIST_MOVIE
        //when
        val results = sut.getMovie() as Results.Success
        //then
        coVerify { dataSource.getMovie() }
        assertEquals(DUMMY_LIST_MOVIE.map { it.toDomain() }[0], results.data[0])
        assertEquals(DUMMY_LIST_MOVIE.map { it.toDomain() }[0].id, results.data[0].id)
        assertEquals(
            DUMMY_LIST_MOVIE.map { it.toDomain() }[0].poster_path,
            results.data[0].poster_path
        )
        assertEquals(DUMMY_LIST_MOVIE.map { it.toDomain() }[0].overview, results.data[0].overview)
        assertEquals(DUMMY_LIST_MOVIE.map { it.toDomain() }[0].title, results.data[0].title)
    }

    @Test
    fun `getMovie failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getMovie() } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getMovie() as Results.Error
        //then
        coVerify { dataSource.getMovie() }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `getDetailMovie successfully return value`() = runTest {
        //given
        coEvery { dataSource.getDetailMovie(1) } returns DUMMY_DETAIL_MOVIE
        //when
        val results = sut.getDetailMovie(1) as Results.Success
        //then
        coVerify { dataSource.getDetailMovie(1) }
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain(), results.data)
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain().localId, results.data.localId)
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain().movieId, results.data.movieId)
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain().backdropPath, results.data.backdropPath)
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain().overview, results.data.overview)
        assertEquals(DUMMY_DETAIL_MOVIE.toDomain().title, results.data.title)
    }

    @Test
    fun `getDetailMovie failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getDetailMovie(1) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getDetailMovie(1) as Results.Error
        //then
        coVerify { dataSource.getDetailMovie(1) }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }
}
package com.junemon.compose_stable.domain.repository

import com.junemon.compose_stable.DummyNews.DUMMY_LIST_NEWS_RESPONSE
import com.junemon.compose_stable.datasource.NewsRemoteDataSource
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.domain.Results
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRepositoryImplTest {

    lateinit var sut: NewsRepository

    private val dataSource: NewsRemoteDataSource = mockk()

    @Before
    fun setUp() {
        sut = NewsRepositoryImpl(dataSource)
    }

    @Test
    fun `getNews successfully return value`() = runTest {
        //given
        coEvery { dataSource.getNews() } returns DUMMY_LIST_NEWS_RESPONSE
        //when
        val results = sut.getNews() as Results.Success
        //then
        coVerify { dataSource.getNews() }
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsAuthor,
            results.data[0].newsAuthor
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsContent,
            results.data[0].newsContent
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsImage,
            results.data[0].newsImage
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsTitle,
            results.data[0].newsTitle
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsDescription,
            results.data[0].newsDescription
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].publishedAt,
            results.data[0].publishedAt
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].sourceName,
            results.data[0].sourceName
        )
    }

    @Test
    fun `getNews failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.getNews() } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.getNews() as Results.Error
        //then
        coVerify { dataSource.getNews() }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }

    @Test
    fun `searchNews successfully return value`() = runTest {
        //given
        coEvery { dataSource.searchNews("a") } returns DUMMY_LIST_NEWS_RESPONSE
        //when
        val results = sut.searchNews("a") as Results.Success
        //then
        coVerify { dataSource.searchNews("a") }
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsAuthor,
            results.data[0].newsAuthor
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsContent,
            results.data[0].newsContent
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsImage,
            results.data[0].newsImage
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsTitle,
            results.data[0].newsTitle
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].newsDescription,
            results.data[0].newsDescription
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].publishedAt,
            results.data[0].publishedAt
        )
        assertEquals(
            DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }[0].sourceName,
            results.data[0].sourceName
        )
    }

    @Test
    fun `searchNews failed and Throw exception`() = runTest {
        //given
        coEvery { dataSource.searchNews("a") } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        val results = sut.searchNews("a") as Results.Error
        //then
        coVerify { dataSource.searchNews("a") }
        assertEquals(NetworkConstant.NETWORK_ERROR, results.errorMessage)
    }
}
package com.junemon.compose_stable.datasource

import com.junemon.compose_stable.DummyNews.DUMMY_LIST_NEWS_RESPONSE
import com.junemon.compose_stable.DummyNews.DUMMY_NEWS_BASE_RESPONSE
import com.junemon.compose_stable.DummyPokemon
import com.junemon.compose_stable.datasource.network.NetworkConstant
import com.junemon.compose_stable.datasource.network.NewsApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRemoteDataSourceImplTest {

    lateinit var sut: NewsRemoteDataSource
    private val api: NewsApi = mockk()

    @Before
    fun setUp() {
        sut = NewsRemoteDataSourceImpl(api)
    }

    @Test
    fun `getNews successfully return value`()= runTest{
        //given
        coEvery { api.getNews() } returns DUMMY_NEWS_BASE_RESPONSE
        //when
        val results = sut.getNews()
        //then
        coVerify { api.getNews() }
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles, results)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[0].newsAuthor, results[0].newsAuthor)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[1].newsAuthor, results[1].newsAuthor)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[2].newsAuthor, results[2].newsAuthor)
    }

    @Test
    fun `getNews failed and Throw exception`()= runTest{
        //given
        var exceptionThrown = false
        coEvery { api.getNews() } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        try {
            sut.getNews()
        } catch(exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NetworkConstant.NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.getNews() }
        Assert.assertTrue(exceptionThrown)
    }


    @Test
    fun `searchNews successfully return value`()= runTest{
        //given
        coEvery { api.searchNews(searchQuery = "a") } returns DUMMY_NEWS_BASE_RESPONSE
        //when
        val results = sut.searchNews("a")
        //then
        coVerify { api.searchNews(searchQuery = any()) }
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles, results)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[0].newsAuthor, results[0].newsAuthor)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[1].newsAuthor, results[1].newsAuthor)
        Assert.assertEquals(DUMMY_NEWS_BASE_RESPONSE.articles[2].newsAuthor, results[2].newsAuthor)
    }

    @Test
    fun `searchNews failed and Throw exception`()= runTest{
        //given
        var exceptionThrown = false
        coEvery { api.searchNews(searchQuery = any()) } throws Exception(NetworkConstant.NETWORK_ERROR)
        //when
        try {
            sut.searchNews("a")
        } catch(exception: Exception) {
            // Maybe put some assertions on the exception here.
            Assert.assertEquals(exception.message, NetworkConstant.NETWORK_ERROR)
            exceptionThrown = true
        }
        //then
        coVerify { api.searchNews(searchQuery = any()) }
        Assert.assertTrue(exceptionThrown)
    }

}
package com.junemon.compose_stable.presentation.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.junemon.compose_stable.core.datasource.network.NetworkConstant.NETWORK_ERROR
import com.junemon.compose_stable.core.domain.Results
import com.junemon.compose_stable.core.domain.model.news.News
import com.junemon.compose_stable.core.domain.repository.NewsRepository
import com.junemon.compose_stable.core.presentation.NewsViewModel
import com.junemon.compose_stable.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class FailedNewsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: NewsViewModel
    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        repository = mockk {
            coEvery { this@mockk.getNews() } returns Results.Error(NETWORK_ERROR)
            coEvery { this@mockk.searchNews(any()) } returns Results.Error(NETWORK_ERROR)
        }
        sut = NewsViewModel(repository)
    }

    @Test
    fun `testing value of userQuery`() = runTest {
        Assert.assertEquals("", sut.userQuery.value) // assert initial value

        sut.setSearch("abc")
        Assert.assertEquals("abc", sut.userQuery.value) // assert initial value

    }

    @Test
    fun `getNews return error inside init viewModel`() = runTest {
        Assert.assertEquals(NETWORK_ERROR, sut.uiStateOfNews.failedMessage)
        Assert.assertEquals(false, sut.uiStateOfNews.isLoading)
        Assert.assertEquals(emptyList<News>(), sut.uiStateOfNews.data)
    }

    @Test
    fun `searchNews return error inside init viewModel using userQuery value`() = runTest {
        sut.searchNews("abc")

        Assert.assertEquals(emptyList<News>(), sut.uiStateOfSearchNews.data) // assert state value
        Assert.assertEquals(false, sut.uiStateOfSearchNews.isLoading) // assert state value
        Assert.assertEquals(
            NETWORK_ERROR,
            sut.uiStateOfSearchNews.failedMessage
        ) // assert state value

    }
}
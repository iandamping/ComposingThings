package com.junemon.compose_stable.presentation.news

import com.junemon.compose_stable.DummyNews
import com.junemon.compose_stable.domain.Results
import com.junemon.compose_stable.domain.repository.NewsRepository
import com.junemon.compose_stable.presentation.NewsViewModel
import com.junemon.compose_stable.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
class SuccessNewsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    lateinit var sut: NewsViewModel
    lateinit var repository:NewsRepository

    @Before
    fun setUp() {
        repository = mockk{
            coEvery { this@mockk.getNews() } returns Results.Success(DummyNews.DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() })
            coEvery { this@mockk.searchNews(any()) } returns Results.Success(DummyNews.DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() })
        }
        sut = NewsViewModel(repository)
    }

    @Test
    fun `asserting value of userQuery`() = runTest {
        assertEquals("", sut.userQuery.value) // assert initial value

        sut.setSearch("abc")
        assertEquals("abc", sut.userQuery.value) // assert initial value

    }

    @Test
    fun `getNews return success inside init viewModel`() = runTest{
        assertEquals(DummyNews.DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() }, sut.uiStateOfNews.data)
        assertEquals(false, sut.uiStateOfNews.isLoading)
        assertEquals("", sut.uiStateOfNews.failedMessage)
    }



    @Test
    fun `searchNews return success inside init viewModel using userQuery value`() = runTest {
        sut.searchNews("abc")

        assertEquals(
            DummyNews.DUMMY_LIST_NEWS_RESPONSE.map { it.toDomain() },
            sut.uiStateOfSearchNews.data
        ) // assert state value
        assertEquals(false, sut.uiStateOfSearchNews.isLoading) // assert state value
        assertEquals("", sut.uiStateOfSearchNews.failedMessage) // assert state value

    }
}
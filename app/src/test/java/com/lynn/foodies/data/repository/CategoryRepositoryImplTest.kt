package com.lynn.foodies.data.repository

import app.cash.turbine.test
import com.lynn.foodies.data.datasource.category.CategoryDataSource
import com.lynn.foodies.data.source.network.model.category.CategoriesResponse
import com.lynn.foodies.data.source.network.model.category.CategoryItemResponse
import com.lynn.foodies.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CategoryRepositoryImplTest {
    @MockK
    lateinit var ds: CategoryDataSource

    lateinit var repo: CategoryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CategoryRepositoryImpl(ds)
    }

    @Test
    fun `get Categories loading`() {
        val c1 = CategoryItemResponse(
            name = "burger",
            imgUrl = "imisi"
        )
        val c2 = CategoryItemResponse(
            name = "nasi",
            imgUrl = "imisi"
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(150)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun `get Categories success`() {
        val c1 = CategoryItemResponse(
            name = "burger",
            imgUrl = null
        )
        val c2 = CategoryItemResponse(
            name = "nasi",
            imgUrl = null
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun `get Categories error`() {
        runTest {
            coEvery { ds.getCategories() } throws IllegalStateException("erorr")
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun `get Categories empty`() {
        val mockListCategory = listOf<CategoryItemResponse>()
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify { ds.getCategories() }
            }
        }
    }
}
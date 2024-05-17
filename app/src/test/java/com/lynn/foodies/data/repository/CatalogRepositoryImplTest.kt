package com.lynn.foodies.data.repository

import app.cash.turbine.test
import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.model.Cart
import com.lynn.foodies.data.source.network.model.catalog.CatalogItemResponse
import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse
import com.lynn.foodies.data.source.network.model.checkout.CheckoutItemPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutResponse
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

class CatalogRepositoryImplTest {
    @MockK
    lateinit var ds: CatalogDataSource

    lateinit var repo: CatalogRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CatalogRepositoryImpl(ds)
    }

    @Test
    fun `getCatalogs success`() {
        val mockCatalog1 = CatalogItemResponse(
            imgUrl = "aediakm",
            name = "nansdj",
            priceFormat = "Rp10.000",
            price = 10000,
            detail = "akndksm",
            location = "jndsajnd"
        )
        val mockCatalog2 = CatalogItemResponse(
            imgUrl = "aediakm",
            name = "nansdj",
            priceFormat = "Rp10.000",
            price = 10000,
            detail = "akndksm",
            location = "jndsajnd"
        )
        val mockListCatalog = listOf(mockCatalog1, mockCatalog2)
        val mockResponse = mockk<CatalogResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { ds.getCatalogs(any()) } returns mockResponse
            repo.getCatalogs().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getCatalogs(any()) }
            }
        }
    }

    @Test
    fun `getCatalogs loading`() {
        val mockCatalog1 = CatalogItemResponse(
            imgUrl = "aediakm",
            name = "nansdj",
            priceFormat = "Rp10.000",
            price = 10000,
            detail = "akndksm",
            location = "jndsajnd"
        )
        val mockCatalog2 = CatalogItemResponse(
            imgUrl = "aediakm",
            name = "nansdj",
            priceFormat = "Rp10.000",
            price = 10000,
            detail = "akndksm",
            location = "jndsajnd"
        )
        val mockListCatalog = listOf(mockCatalog1, mockCatalog2)
        val mockResponse = mockk<CatalogResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { ds.getCatalogs(any()) } returns mockResponse
            repo.getCatalogs().map {
                delay(100)
                it
            }.test {
                delay(150)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.getCatalogs(any()) }
            }
        }
    }

    @Test
    fun `getCatalogs error`() {
        runTest {
            coEvery { ds.getCatalogs(any()) } throws IllegalStateException("error")
            repo.getCatalogs().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { ds.getCatalogs(any()) }
            }
        }
    }

    @Test
    fun `getCatalogs empty`() {
        val mockListCatalog = listOf<CatalogItemResponse>()
        val mockResponse = mockk<CatalogResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { ds.getCatalogs(any()) } returns mockResponse
            repo.getCatalogs().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify { ds.getCatalogs(any()) }
            }
        }
    }

    @Test
    fun `createOrder success`() {
        val mockCart = listOf<Cart>()
        val mockUsername = "adul"
        val mockTotal = 1000
        val mockResponse = CheckoutResponse(
            code = 100,
            message = "success",
            status = true
        )
        runTest {
            coEvery { ds.createOrder(any()) } returns mockResponse
            repo.createOrder(mockCart, mockUsername, mockTotal).map {
                delay(100)
                it
            }.test {
                delay(220)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.createOrder(any()) }
            }
        }
    }

    @Test
    fun `createOrder failed`() {
        val mockCart = listOf<Cart>()
        val mockUsername = "adul"
        val mockTotal = 1000
        val mockResponse = CheckoutResponse(
            code = 100,
            message = "success",
            status = false
        )
        runTest {
            coEvery { ds.createOrder(any()) } returns mockResponse
            repo.createOrder(mockCart, mockUsername, mockTotal).map {
                delay(100)
                it
            }.test {
                delay(220)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.createOrder(any()) }
            }
        }
    }
}
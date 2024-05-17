package com.lynn.foodies.data.datasource.catalog

import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse
import com.lynn.foodies.data.source.network.model.checkout.CheckoutItemPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutResponse
import com.lynn.foodies.data.source.network.services.FoodiesApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CatalogApiDataSourceTest {
    @MockK
    lateinit var service: FoodiesApiService

    private lateinit var dataSource: CatalogDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = CatalogApiDataSource(service)
    }

    @Test
    fun getCatalogs() {
        runTest {
            val mockResponse = mockk<CatalogResponse>(relaxed = true)
            coEvery { service.getCatalog(any()) } returns mockResponse
            val actualResult = dataSource.getCatalogs("burger")
            coVerify { service.getCatalog(any()) }
            assertEquals(actualResult, mockResponse)
        }
    }

    @Test
    fun createOrder() {
        val mockItem = CheckoutItemPayload(
            name = "adsda",
            quantity = 2,
            notes = "adsda",
            price = 10000
        )
        val mockPayload = CheckoutRequestPayload(
            username = "aaa",
            orders = listOf(mockItem,mockItem),
            total = 100000
        )
        runTest {
            val mockResponse = mockk<CheckoutResponse>(relaxed = true)
            coEvery { service.createOrder(any()) } returns mockResponse
            val actualResult = dataSource.createOrder(mockPayload)
            coVerify { service.createOrder(any()) }
            assertEquals(actualResult, mockResponse)
        }

    }
}
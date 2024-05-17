package com.lynn.foodies.data.datasource.catalog

import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse
import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutResponse

interface CatalogDataSource {
    suspend fun getCatalogs(categoryName: String?): CatalogResponse
    suspend fun createOrder(payload: CheckoutRequestPayload): CheckoutResponse
}
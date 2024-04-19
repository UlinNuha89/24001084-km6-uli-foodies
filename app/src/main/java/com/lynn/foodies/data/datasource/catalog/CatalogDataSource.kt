package com.lynn.foodies.data.datasource.catalog

import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutResponse
import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse

interface CatalogDataSource {
    suspend fun getCatalogs (categoryName: String? = null): CatalogResponse
    suspend fun createOrder(payload : CheckoutRequestPayload) : CheckoutResponse
}
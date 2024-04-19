package com.lynn.foodies.data.datasource.catalog

import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutResponse
import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse
import com.lynn.foodies.data.source.network.services.FoodiesApiService

class CatalogApiDataSource(private val service: FoodiesApiService):CatalogDataSource {
    override suspend fun getCatalogs(categoryName: String?): CatalogResponse {
        return service.getCatalog(categoryName)
    }

    override suspend fun createOrder(payload: CheckoutRequestPayload): CheckoutResponse {
        return service.createOrder(payload)
    }
}
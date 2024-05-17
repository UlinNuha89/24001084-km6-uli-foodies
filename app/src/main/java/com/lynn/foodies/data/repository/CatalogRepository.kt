package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.mapper.toCatalogs
import com.lynn.foodies.data.mapper.toCheckoutItemPayloadList
import com.lynn.foodies.data.model.Cart
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.source.network.model.checkout.CheckoutItemPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload
import com.lynn.foodies.utils.ResultWrapper
import com.lynn.foodies.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    fun getCatalogs(categoryName: String? = null): Flow<ResultWrapper<List<Catalog>>>

    fun createOrder(catalog: List<Cart>, username: String, total: Int): Flow<ResultWrapper<Boolean>>

}

class CatalogRepositoryImpl(private val dataSource: CatalogDataSource) : CatalogRepository {
    override fun getCatalogs(categoryName: String?): Flow<ResultWrapper<List<Catalog>>> {
        return proceedFlow {
            dataSource.getCatalogs(categoryName).data.toCatalogs()
        }
    }

    override fun createOrder(
        catalog: List<Cart>,
        username: String,
        total: Int
    ): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.createOrder(CheckoutRequestPayload(
                username = username,
                total = total,
                orders = catalog.toCheckoutItemPayloadList()
            )).status ?: false
        }
    }
}
package com.lynn.foodies.data.datasource.catalog

import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse

interface CatalogDataSource {
    suspend fun getCatalogs (categoryName: String? = null): CatalogResponse
}
package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.model.Catalog

interface CatalogRepository {
    fun getCatalog(): List<Catalog>
}

class CatalogRepositoryImpl(private val dataSource: CatalogDataSource) : CatalogRepository {
    override fun getCatalog(): List<Catalog> = dataSource.getCatalog()
}
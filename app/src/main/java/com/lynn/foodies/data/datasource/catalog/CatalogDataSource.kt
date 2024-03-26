package com.lynn.foodies.data.datasource.catalog

import com.lynn.foodies.data.model.Catalog

interface CatalogDataSource {
    fun getCatalog(): List<Catalog>
}
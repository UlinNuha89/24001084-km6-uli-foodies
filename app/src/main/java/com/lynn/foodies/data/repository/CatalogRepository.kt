package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.catalog.CatalogDataSource
import com.lynn.foodies.data.mapper.toCatalogs
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.utils.ResultWrapper
import com.lynn.foodies.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    fun getCatalogs(categoryName : String? = null): Flow<ResultWrapper<List<Catalog>>>
}

class CatalogRepositoryImpl(private val dataSource: CatalogDataSource) : CatalogRepository {
    override fun getCatalogs(categoryName: String?): Flow<ResultWrapper<List<Catalog>>> {
        return proceedFlow {
            dataSource.getCatalogs(categoryName).data.toCatalogs()
        }
    }
}
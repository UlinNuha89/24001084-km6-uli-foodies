package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.category.CategoryDataSource
import com.lynn.foodies.data.mapper.toCategories
import com.lynn.foodies.data.model.Category
import com.lynn.foodies.utils.ResultWrapper
import com.lynn.foodies.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<ResultWrapper<List<Category>>>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override fun getCategories(): Flow<ResultWrapper<List<Category>>> {
        return proceedFlow {
            dataSource.getCategories().data.toCategories()
        }
    }
}
package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.category.CategoryDataSource
import com.lynn.foodies.data.model.Category

interface CategoryRepository {
    fun getCategory(): List<Category>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override fun getCategory(): List<Category> = dataSource.getCategory()
}
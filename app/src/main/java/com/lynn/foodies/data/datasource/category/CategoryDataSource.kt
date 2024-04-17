package com.lynn.foodies.data.datasource.category

import com.lynn.foodies.data.source.network.model.category.CategoriesResponse

interface CategoryDataSource {
    suspend fun getCategories(): CategoriesResponse
}
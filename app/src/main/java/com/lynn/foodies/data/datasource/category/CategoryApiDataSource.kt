package com.lynn.foodies.data.datasource.category

import com.lynn.foodies.data.source.network.model.category.CategoriesResponse
import com.lynn.foodies.data.source.network.services.FoodiesApiService

class CategoryApiDataSource(private val service: FoodiesApiService):CategoryDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}
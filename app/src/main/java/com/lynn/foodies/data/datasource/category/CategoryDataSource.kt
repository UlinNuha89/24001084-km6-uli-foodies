package com.lynn.foodies.data.datasource.category

import com.lynn.foodies.data.model.Category

interface CategoryDataSource {
    fun getCategory(): List<Category>
}
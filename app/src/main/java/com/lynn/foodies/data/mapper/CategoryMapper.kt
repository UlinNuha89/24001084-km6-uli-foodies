package com.lynn.foodies.data.mapper

import com.lynn.foodies.data.model.Category
import com.lynn.foodies.data.source.network.model.category.CategoryItemResponse

fun CategoryItemResponse?.toCategory() =
    Category(
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty()
    )

fun Collection<CategoryItemResponse>?.toCategories() =
    this?.map { it.toCategory() } ?: listOf()
package com.lynn.foodies.data.mapper

import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.source.network.model.catalog.CatalogItemResponse

fun CatalogItemResponse?.toCatalog() =
    Catalog(
        name = this?.name.orEmpty(),
        rating = 4.0,
        price = this?.price ?:0,
        priceFormat = this?.priceFormat.orEmpty(),
        imageUrl = this?.imgUrl.orEmpty(),
        detail = this?.detail.orEmpty(),
        location = this?.location.orEmpty(),
        locUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77",
        )

fun Collection<CatalogItemResponse>?.toCatalogs() = this?.map {
    it.toCatalog()
} ?: listOf()
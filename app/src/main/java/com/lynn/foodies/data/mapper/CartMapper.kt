package com.lynn.foodies.data.mapper

import com.lynn.foodies.data.model.Cart
import com.lynn.foodies.data.source.local.database.entity.CartEntity

fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    catalogId = this?.catalogId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    catalogName = this?.catalogName.orEmpty(),
    catalogPrice = this?.catalogPrice ?: 0.0,
    catalogImageUrl = this?.catalogImageUrl.orEmpty()
)

fun CartEntity?.toCart() = Cart(
    id = this?.id,
    catalogId = this?.catalogId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    catalogName = this?.catalogName.orEmpty(),
    catalogPrice = this?.catalogPrice ?: 0.0,
    catalogImageUrl = this?.catalogImageUrl.orEmpty()
)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }
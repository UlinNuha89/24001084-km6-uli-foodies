package com.lynn.foodies.data.mapper

import com.lynn.foodies.data.model.Cart
import com.lynn.foodies.data.source.local.database.entity.CartEntity
import com.lynn.foodies.data.source.network.model.checkout.CheckoutItemPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload

fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    catalogId = this?.catalogId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    catalogName = this?.catalogName.orEmpty(),
    catalogPrice = this?.catalogPrice ?: 0.0,
    catalogImageUrl = this?.catalogImageUrl.orEmpty(),
    itemNotes = this?.itemNotes.orEmpty()
)

fun CartEntity?.toCart() = Cart(
    id = this?.id,
    catalogId = this?.catalogId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    catalogName = this?.catalogName.orEmpty(),
    catalogPrice = this?.catalogPrice ?: 0.0,
    catalogImageUrl = this?.catalogImageUrl.orEmpty(),
    itemNotes = this?.itemNotes.orEmpty()
)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }

fun Cart?.toCheckoutItemPayload() = CheckoutItemPayload(
    name = this?.catalogName,
    quantity = this?.itemQuantity ?:0,
    notes = this?.itemNotes,
    price = this?.catalogPrice?.toInt() ?:0
)

fun List<Cart?>.toCheckoutItemPayloadList() = this.map { it.toCheckoutItemPayload() }
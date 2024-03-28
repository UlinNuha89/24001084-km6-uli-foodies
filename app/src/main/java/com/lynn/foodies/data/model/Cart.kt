package com.lynn.foodies.data.model

data class Cart(
    var id: Int? = null,
    var catalogId: String? = null,
    var catalogName: String,
    var catalogImageUrl: String,
    var catalogPrice: Double,
    var itemQuantity: Int = 0,
    var itemNotes: String? = null
)
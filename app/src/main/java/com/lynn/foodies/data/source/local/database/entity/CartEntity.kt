package com.lynn.foodies.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "product_id")
    var catalogId: String? = null,
    @ColumnInfo(name = "product_name")
    var catalogName: String,
    @ColumnInfo(name = "product_img_url")
    var catalogImageUrl: String,
    @ColumnInfo(name = "product_price")
    var catalogPrice: Double,
    @ColumnInfo(name = "item_quantity")
    var itemQuantity: Int = 0,
    @ColumnInfo(name = "item_notes")
    var itemNotes: String? = null,
)

package com.lynn.foodies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Catalog(
    var id: String? = UUID.randomUUID().toString(),
    var imageUrl: String,
    var name: String,
    var priceFormat: String,
    var price: Int,
    var detail: String,
    var rating: Double,
    var location: String,
    var locUrl: String
) :Parcelable

package com.lynn.foodies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Catalog(
    var id: String? = UUID.randomUUID().toString(),
    var name: String,
    var imageUrl: String,
    var price: Double,
    var desc: String,
    var rating: Double,
    var location: String,
    var locUrl: String
) :Parcelable

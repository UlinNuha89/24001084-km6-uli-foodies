package com.lynn.foodies.data.source.network.model.catalog

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CatalogItemResponse(
    @SerializedName("image_url")
    val imgUrl: String?,
    @SerializedName("nama")
    val name: String?,
    @SerializedName("harga_format")
    val priceFormat: String?,
    @SerializedName("harga")
    val price: Int?,
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("alamat_resto")
    val location: String?,
)
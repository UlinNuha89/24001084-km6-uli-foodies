package com.lynn.foodies.data.source.network.model.checkout

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class CheckoutItemPayload(
    @SerializedName("nama")
    val name: String?,
    @SerializedName("qty")
    val quantity: Int,
    @SerializedName("catatan")
    val notes: String?,
    @SerializedName("harga")
    val price: Int,
)

package com.lynn.foodies.data.source.network.model.catalog

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CatalogResponse(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val `data`: List<CatalogItemResponse>?,
)
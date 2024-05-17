package com.lynn.foodies.data.source.network.services

import com.lynn.foodies.data.source.network.model.checkout.CheckoutRequestPayload
import com.lynn.foodies.data.source.network.model.checkout.CheckoutResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.lynn.foodies.BuildConfig
import com.lynn.foodies.data.source.network.model.catalog.CatalogResponse
import com.lynn.foodies.data.source.network.model.category.CategoriesResponse
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodiesApiService {

    @GET("category")
    suspend fun getCategories(): CategoriesResponse

    @GET("listmenu")
    suspend fun getCatalog(
        @Query("c") category: String? = null
    ):CatalogResponse

    @POST("order")
    suspend fun createOrder(@Body payload: CheckoutRequestPayload): CheckoutResponse

    companion object {
        @JvmStatic
        operator fun invoke(): FoodiesApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(FoodiesApiService::class.java)
        }
    }
}
package com.lynn.foodies.presentation.detailcatalog

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.repository.CartRepository
import com.lynn.foodies.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class DetailCatalogViewModel(
    private val extras: Bundle?,
    private val cartRepository: CartRepository
) : ViewModel() {
    val catalog = extras?.getParcelable<Catalog>(DetailCatalogActivity.EXTRAS_ITEM)

    val productCountLiveData = MutableLiveData(0).apply {
        postValue(0)
    }

    val priceLiveData = MutableLiveData<Double>().apply {
        postValue(0.0)
    }

    fun add() {
        val count = (productCountLiveData.value ?: 0) + 1
        productCountLiveData.postValue(count)
        priceLiveData.postValue(catalog?.price?.toDouble()?.times(count) ?: 0.0)
    }

    fun minus() {
        if ((productCountLiveData.value ?: 0) > 0) {
            val count = (productCountLiveData.value ?: 0) - 1
            productCountLiveData.postValue(count)
            priceLiveData.postValue(catalog?.price?.toDouble()?.times(count) ?: 0.0)
        }
    }

    fun goToGoogleMaps(context: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(catalog?.locUrl)
        )
        context.startActivity(intent)
    }

    fun addToCart(): LiveData<ResultWrapper<Boolean>> {
        return catalog?.let {
            val quantity = productCountLiveData.value ?: 0
            cartRepository.createCart(it, quantity).asLiveData(Dispatchers.IO)
        } ?: liveData { emit(ResultWrapper.Error(IllegalStateException("Product not found"))) }
    }

}
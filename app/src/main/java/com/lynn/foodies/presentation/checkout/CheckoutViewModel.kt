package com.lynn.foodies.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lynn.foodies.data.repository.CartRepository
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val productRepository: CatalogRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val checkoutData = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)

    var price: Int = 0
    fun checkoutCart() = productRepository.createOrder(
        username = userRepository.getCurrentUser()?.fullName.orEmpty(),
        total = price,
        catalog = checkoutData.value?.payload?.first.orEmpty()
    ).asLiveData(Dispatchers.IO)


    fun deleteAllCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.deleteAll().collect()
        }
    }

}
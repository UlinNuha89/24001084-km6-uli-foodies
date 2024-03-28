package com.lynn.foodies.data.repository

import com.lynn.foodies.data.datasource.cart.CartDataSource
import com.lynn.foodies.data.mapper.toCartEntity
import com.lynn.foodies.data.mapper.toCartList
import com.lynn.foodies.data.model.Cart
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.source.local.database.entity.CartEntity
import com.lynn.foodies.utils.ResultWrapper
import com.lynn.foodies.utils.proceed
import com.lynn.foodies.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


interface CartRepository {
    fun getUserCartData(): Flow<ResultWrapper<Pair<List<Cart>, Double>>>
    fun createCart(
        catalog: Catalog,
        quantity: Int,
        notes: String? = null
    ): Flow<ResultWrapper<Boolean>>

    fun decreaseCart(item: Cart): Flow<ResultWrapper<Boolean>>
    fun increaseCart(item: Cart): Flow<ResultWrapper<Boolean>>
    fun setCartNotes(item: Cart): Flow<ResultWrapper<Boolean>>
    fun deleteCart(item: Cart): Flow<ResultWrapper<Boolean>>

}

class CartRepositoryImpl(private val cartDataSource: CartDataSource) : CartRepository {

    override fun getUserCartData(): Flow<ResultWrapper<Pair<List<Cart>, Double>>> {
        return cartDataSource.getAllCarts()
            .map {
                //mapping into cart list and sum the total price
                proceed {
                    val result = it.toCartList()
                    val totalPrice = result.sumOf { it.catalogPrice * it.itemQuantity }
                    Pair(result, totalPrice)
                }
            }.map {
                //map to check when list is empty
                if (it.payload?.first?.isEmpty() == false) return@map it
                ResultWrapper.Empty(it.payload)
            }.onStart {
                emit(ResultWrapper.Loading())
                delay(2000)
            }
    }

    override fun createCart(
        catalog: Catalog,
        quantity: Int,
        notes: String?
    ): Flow<ResultWrapper<Boolean>> {
        return catalog.id?.let { catalogId ->
            proceedFlow {
                val affectedRow = cartDataSource.insertCart(
                    CartEntity(
                        catalogId = catalogId,
                        itemQuantity = quantity,
                        catalogName = catalog.name,
                        catalogImageUrl = catalog.imageUrl,
                        catalogPrice = catalog.price,
                        itemNotes = notes
                    )
                )
                affectedRow > 0
            }
        } ?: flow {
            emit(ResultWrapper.Error(IllegalStateException("Product ID not Found")))
        }
    }

    override fun decreaseCart(item: Cart): Flow<ResultWrapper<Boolean>> {
        val modifiedCard = item.copy().apply {
            itemQuantity -= 1
        }
        return if (modifiedCard.itemQuantity <= 0) {
            proceedFlow { cartDataSource.deleteCart(item.toCartEntity()) > 0 }
        } else {
            proceedFlow { cartDataSource.updateCart(modifiedCard.toCartEntity()) > 0 }
        }
    }

    override fun increaseCart(item: Cart): Flow<ResultWrapper<Boolean>> {
        val modifiedCard = item.copy().apply {
            itemQuantity += 1
        }
        return proceedFlow { cartDataSource.updateCart(modifiedCard.toCartEntity()) > 0 }
    }

    override fun setCartNotes(item: Cart): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { cartDataSource.updateCart(item.toCartEntity()) > 0 }
    }

    override fun deleteCart(item: Cart): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { cartDataSource.deleteCart(item.toCartEntity()) > 0 }
    }
}
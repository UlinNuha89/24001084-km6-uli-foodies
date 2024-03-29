package com.lynn.foodies.presentation.detailcatalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.lynn.foodies.R
import com.lynn.foodies.data.datasource.cart.CartDataSource
import com.lynn.foodies.data.datasource.cart.CartDatabaseDataSource
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.data.repository.CartRepository
import com.lynn.foodies.data.repository.CartRepositoryImpl
import com.lynn.foodies.data.source.local.database.AppDatabase
import com.lynn.foodies.databinding.ActivityDetailCatalogBinding
import com.lynn.foodies.utils.GenericViewModelFactory
import com.lynn.foodies.utils.proceedWhen
import com.lynn.foodies.utils.toIndonesianFormat

class DetailCatalogActivity : AppCompatActivity() {

    private val binding: ActivityDetailCatalogBinding by lazy {
        ActivityDetailCatalogBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailCatalogViewModel by viewModels {
        val db = AppDatabase.getInstance(this)
        val ds: CartDataSource = CartDatabaseDataSource(db.cartDao())
        val rp: CartRepository = CartRepositoryImpl(ds)
        GenericViewModelFactory.create(
            DetailCatalogViewModel(intent?.extras, rp)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindView(viewModel.catalog)
        setClickListener()
        observeData()
    }

    companion object {
        const val EXTRAS_ITEM = "EXTRAS_ITEM"
        fun startActivity(context: Context, catalog: Catalog) {
            val intent = Intent(context, DetailCatalogActivity::class.java)
            intent.putExtra(EXTRAS_ITEM, catalog)
            context.startActivity(intent)
        }
    }

    private fun observeData() {
        viewModel.priceLiveData.observe(this) {
            binding.layoutBottomDetail.btnAddToCart.isEnabled = it != 0.0
            setAddToCartText(price = it)
        }
        viewModel.productCountLiveData.observe(this) {
            binding.layoutBottomDetail.tvOrderCount.text = it.toString()
        }
    }

    private fun bindView(item: Catalog?) {
        item?.let {
            binding.ivDetailImage.load(it.imageUrl) {
                crossfade(true)
            }
            binding.tvDetailTitle.text = it.name
            binding.tvDetailPrice.text = it.price.toIndonesianFormat()
            binding.tvDetailDesc.text = it.desc
            binding.tvDetailLocation.text = it.location
            setAddToCartText(it.price)
        }
    }

    private fun setAddToCartText(price: Double?) {
        binding.layoutBottomDetail.btnAddToCart.text =
            getString(R.string.text_add_to_cart) + price?.toIndonesianFormat()
    }

    private fun setClickListener() {
        binding.layoutBottomDetail.ivAddButton.setOnClickListener {
            viewModel.add()
        }
        binding.layoutBottomDetail.ivMinusButton.setOnClickListener {
            viewModel.minus()
        }
        binding.tvDetailLocation.setOnClickListener {
            viewModel.goToGoogleMaps(this)
        }
        binding.cardBackArrow.setOnClickListener {
            onBackPressed()
        }
        binding.layoutBottomDetail.btnAddToCart.setOnClickListener {
            val count: CharSequence = binding.layoutBottomDetail.tvOrderCount.text
            addProductToCart(count)

        }
    }

    private fun addProductToCart(count: CharSequence) {
        viewModel.addToCart().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_add_to_cart_on_pressed, count), Toast.LENGTH_SHORT
                    ).show()
                    finish()
                },
                doOnError = {
                    Toast.makeText(this, getString(R.string.add_to_cart_failed), Toast.LENGTH_SHORT)
                        .show()
                },
                doOnLoading = {
                    Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
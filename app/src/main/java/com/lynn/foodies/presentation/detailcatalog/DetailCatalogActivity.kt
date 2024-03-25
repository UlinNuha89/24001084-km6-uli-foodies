package com.lynn.foodies.presentation.detailcatalog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lynn.foodies.R
import com.lynn.foodies.databinding.ActivityCheckoutBinding
import com.lynn.foodies.databinding.ActivityDetailCatalogBinding

class DetailCatalogActivity : AppCompatActivity() {

    private  val binding : ActivityDetailCatalogBinding by lazy {
        ActivityDetailCatalogBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
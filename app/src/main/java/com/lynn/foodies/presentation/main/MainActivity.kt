package com.lynn.foodies.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.catnip.firebaseauthexample.data.network.firebase.auth.FirebaseAuthDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.lynn.foodies.R
import com.lynn.foodies.data.repository.UserRepositoryImpl
import com.lynn.foodies.databinding.ActivityMainBinding
import com.lynn.foodies.presentation.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun viewModel(): MainViewModel {
        val firebaseAuth = FirebaseAuth.getInstance()
        val dataSource = FirebaseAuthDataSourceImpl(firebaseAuth)
        val repo = UserRepositoryImpl(dataSource)
        return MainViewModel(repo)
    }

    private val isLogin = viewModel().isLoggedIn()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.menu_tab_profile -> {
                    if (!isLogin) {
                        navigateToLogin()
                        navigateToHome(controller)
                    }
                }
            }

        }
    }

    private fun navigateToHome(controller:NavController) {
        controller.navigate(R.id.menu_tab_home)
    }


    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
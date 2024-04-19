package com.lynn.foodies.presentation.main

import androidx.lifecycle.ViewModel
import com.lynn.foodies.data.repository.UserRepository

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun isLoggedIn() = repository.isLoggedIn()

}
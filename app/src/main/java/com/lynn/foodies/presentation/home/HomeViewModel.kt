package com.lynn.foodies.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.UserRepository
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.data.source.local.pref.UserPreferenceImpl
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val catalogRepository: CatalogRepository,
    private val userPreference: UserPreference,
    private val userRepository: UserRepository
) : ViewModel() {

    val isLoggedIn = userRepository.isLoggedIn()
    fun getUsername() = userRepository.getCurrentUser()?.fullName

    var catalogName :String? =null

    fun setPref(isUsingGridMode: Boolean) =
        userPreference.setUsingGridMode(isUsingGridMode)

    fun getCategories() = categoryRepository.getCategories().asLiveData(Dispatchers.IO)
    fun getCatalogs(categoryName:String? = null) =
        catalogRepository.getCatalogs(categoryName).asLiveData(Dispatchers.IO)

    private val _isUsingGridMode = MutableLiveData(
        userPreference.isUsingGridMode()
    )
    val isUsingGridMode: LiveData<Boolean>
        get() = _isUsingGridMode

    fun changeListMode() {
        val currentValue = _isUsingGridMode.value ?: false
        _isUsingGridMode.postValue(!currentValue)
    }

}
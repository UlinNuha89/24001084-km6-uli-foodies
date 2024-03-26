package com.lynn.foodies.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CategoryRepository

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val catalogRepository: CatalogRepository
) : ViewModel() {

    fun getCategory() = categoryRepository.getCategory()
    fun getCatalog() = catalogRepository.getCatalog()

    private val _isUsingGridMode = MutableLiveData(true)
    val isUsingGridMode: LiveData<Boolean>
        get() = _isUsingGridMode

    fun changeListMode() {
        val currentValue = _isUsingGridMode.value ?: false
        _isUsingGridMode.postValue(!currentValue)
    }

}
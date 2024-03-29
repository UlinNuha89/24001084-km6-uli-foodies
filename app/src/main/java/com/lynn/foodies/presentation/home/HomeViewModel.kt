package com.lynn.foodies.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.data.source.local.pref.UserPreferenceImpl

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val catalogRepository: CatalogRepository,
    context: Context
) : ViewModel() {

    fun getPref(context: Context): Boolean = UserPreferenceImpl(context).isUsingGridMode()
    fun setPref(context: Context, isUsingGridMode: Boolean) =
        UserPreferenceImpl(context).setUsingGridMode(isUsingGridMode)

    fun getCategory() = categoryRepository.getCategory()
    fun getCatalog() = catalogRepository.getCatalog()

    private val _isUsingGridMode = MutableLiveData(
        getPref(context)
    )
    val isUsingGridMode: LiveData<Boolean>
        get() = _isUsingGridMode

    fun changeListMode() {
        val currentValue = _isUsingGridMode.value ?: false
        _isUsingGridMode.postValue(!currentValue)
    }

}
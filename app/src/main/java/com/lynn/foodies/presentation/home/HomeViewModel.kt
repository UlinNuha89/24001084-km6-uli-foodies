package com.lynn.foodies.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.data.source.local.pref.UserPreferenceImpl
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val catalogRepository: CatalogRepository,
    context: Context
) : ViewModel() {


    var isGridMode : Boolean = false
    var catalogName :String? =null

    fun getPref(context: Context): Boolean = UserPreferenceImpl(context).isUsingGridMode()
    fun setPref(context: Context, isUsingGridMode: Boolean) =
        UserPreferenceImpl(context).setUsingGridMode(isUsingGridMode)

    fun getCategories() = categoryRepository.getCategories().asLiveData(Dispatchers.IO)
    fun getCatalogs(categoryName:String? = null) =
        catalogRepository.getCatalogs(categoryName).asLiveData(Dispatchers.IO)

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
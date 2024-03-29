package com.lynn.foodies.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lynn.foodies.data.model.Profile

class ProfileViewModel : ViewModel() {

    val profileData = MutableLiveData(
        Profile(
            name = "Ulin Nuha",
            username = "ulinnn",
            email = "unuha89.un@gmail.com",
            profileImg = "https://avatars.githubusercontent.com/u/156901912?v=4"
        )
    )

    val isEditMode = MutableLiveData(false)

    fun changeEditMode() {
        val currentValue = isEditMode.value ?: false
        isEditMode.postValue(!currentValue)
    }
}
package com.lynn.foodies.data.datasource.user

import com.lynn.foodies.data.model.Profile
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.data.source.local.pref.UserPreferenceImpl

interface UserDataSource {
    fun getUserDataPref(): Profile
    fun setUserDataPref(profile: Profile)
    fun isUsingGridMode(): Boolean
    fun setUsingGridMode(isUsingGridMode: Boolean)
}

class UserPrefDataSource(private val userPreference: UserPreference) : UserDataSource {

    override fun getUserDataPref(): Profile = userPreference.getUserDataPref()

    override fun setUserDataPref(profile: Profile) {
        userPreference.setUserDataPref(profile)
    }

    override fun isUsingGridMode(): Boolean = userPreference.isUsingGridMode()

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        userPreference.setUsingGridMode(isUsingGridMode)
    }

}
package com.lynn.foodies.data.datasource.pref

import com.lynn.foodies.data.source.local.pref.UserPreference

interface UserDataSource {
    fun isUsingGridMode(): Boolean
    fun setUsingGridMode(isUsingGridMode: Boolean)
}

class UserPrefDataSource(private val userPreference: UserPreference) : UserDataSource {

    override fun isUsingGridMode(): Boolean = userPreference.isUsingGridMode()

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        userPreference.setUsingGridMode(isUsingGridMode)
    }

}
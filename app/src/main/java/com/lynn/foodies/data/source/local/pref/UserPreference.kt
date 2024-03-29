package com.lynn.foodies.data.source.local.pref

import android.content.Context
import com.lynn.foodies.data.model.Profile
import com.lynn.foodies.utils.SharedPreferenceUtils
import com.lynn.foodies.utils.SharedPreferenceUtils.get
import com.lynn.foodies.utils.SharedPreferenceUtils.set

interface UserPreference {
    fun isUsingGridMode(): Boolean
    fun setUsingGridMode(isUsingGridMode: Boolean)
    fun getUserDataPref(): Profile
    fun setUserDataPref(profile: Profile)
}

class UserPreferenceImpl(context: Context) : UserPreference {

    private val pref = SharedPreferenceUtils.createPreference(context, PREF_NAME)

    override fun isUsingGridMode(): Boolean {
        return pref.get(KEY_IS_USING_GRID_MODE, true)
    }

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        pref.set(KEY_IS_USING_GRID_MODE, isUsingGridMode)
    }

    override fun getUserDataPref(): Profile {
        return pref.get(KEY_USER_DATA, Profile(
            name = "Ulin Nuha",
            username = "ulinnn",
            email = "unuha89.un@gmail.com",
            profileImg = "https://avatars.githubusercontent.com/u/156901912?v=4"
        ))
    }

    override fun setUserDataPref(profile: Profile) {
        pref.set(KEY_USER_DATA, profile)
    }

    companion object {
        const val PREF_NAME = "foodies-pref"
        const val KEY_IS_USING_GRID_MODE = "KEY_IS_USING_GRID_MODE"
        const val KEY_USER_DATA = "KEY_USER_DATA"
    }

}


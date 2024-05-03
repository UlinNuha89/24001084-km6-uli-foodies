package com.lynn.foodies.data.source.local.pref

import android.content.SharedPreferences
import com.lynn.foodies.data.model.Profile
import com.lynn.foodies.utils.SharedPreferenceUtils.get
import com.lynn.foodies.utils.SharedPreferenceUtils.set

interface UserPreference {
    fun isUsingGridMode(): Boolean
    fun setUsingGridMode(isUsingGridMode: Boolean)
}

class UserPreferenceImpl(private val pref: SharedPreferences) : UserPreference {

    override fun isUsingGridMode(): Boolean = pref.getBoolean(KEY_IS_USING_GRID_MODE, false)

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        pref[KEY_IS_USING_GRID_MODE] = isUsingGridMode
    }


    companion object {
        const val PREF_NAME = "foodies-pref"
        const val KEY_IS_USING_GRID_MODE = "KEY_IS_USING_GRID_MODE"
    }

}


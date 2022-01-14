package com.example.kinwaetest.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.kinwaetest.data.preferences.PreferencesConstants.USER_IS_LOGGED_KEY
import com.example.kinwaetest.data.preferences.PreferencesConstants.USER_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KinwaeAuthPreferences @Inject constructor(
    @ApplicationContext context: Context
) : AuthPreferences {

    companion object {
        const val PREFERENCES_NAME = "KINWAE_AUTH_PREFERENCES"
    }

    private val preferences =
        context.getSharedPreferences(KinwaeApiPreferences.PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveUserLoginStatus(isUserLoggedIn: Boolean) {
        edit {
            putBoolean(USER_IS_LOGGED_KEY, isUserLoggedIn)
        }
    }

    override fun getUserLoginStatus(): Boolean {
        return preferences.getBoolean(USER_IS_LOGGED_KEY, false)
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    override fun deleteAuthInfo() {
        edit {
            remove(USER_IS_LOGGED_KEY)
            remove(USER_KEY)
        }
    }

    override fun saveUserFirstNameAsIdUsingSharedPref(userFirstName: String) {
        edit {
            putString(USER_KEY, userFirstName)
        }
    }

    override fun getUserFirstNameAsIdUsingSharedPref(): String? {
        return preferences.getString(USER_KEY, "")
    }
}
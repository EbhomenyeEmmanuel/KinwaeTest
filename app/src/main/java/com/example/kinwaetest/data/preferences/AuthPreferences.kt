package com.example.kinwaetest.data.preferences

interface AuthPreferences {
    fun saveUserLoginStatus(isUserLoggedIn: Boolean)
    fun getUserLoginStatus(): Boolean
    fun deleteAuthInfo()

    fun saveUserFirstNameAsIdUsingSharedPref(userFirstName: String)
    fun getUserFirstNameAsIdUsingSharedPref(): String?

}
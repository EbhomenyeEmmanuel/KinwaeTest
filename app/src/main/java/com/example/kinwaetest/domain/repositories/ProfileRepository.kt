package com.example.kinwaetest.domain.repositories

import com.example.kinwaetest.data.preferences.ApiPreferences
import com.example.kinwaetest.data.preferences.KinwaeAuthPreferences
import com.example.kinwaetest.domain.model.auth.LoggedInUser

interface ProfileRepository {

    /*
   //TODO("deleteTokenInfo && "deleteAuthInfo from preferences after log out")*
     */
    fun deleteAuthInfo(apiPreferences: ApiPreferences, authPreferences: KinwaeAuthPreferences)

}
package com.example.kinwaetest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.kinwaetest.data.cache.Cache
import com.example.kinwaetest.data.preferences.AuthPreferences
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import com.example.kinwaetest.domain.repositories.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val cache: Cache,
    private val authPreferences: AuthPreferences
) : MainRepository {
    override suspend fun getUserFromDb(): LiveData<LoggedInUser>? {
        val userKey = authPreferences.getUserFirstNameAsIdUsingSharedPref()
        return userKey?.let { cache.getLoggedInUserWithFirstName(it).map { it.toDomain() } }
    }
}
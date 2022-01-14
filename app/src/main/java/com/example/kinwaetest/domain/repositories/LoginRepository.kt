package com.example.kinwaetest.domain.repositories

import com.example.kinwaetest.domain.model.LoginException
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import com.example.kinwaetest.domain.model.auth.LoggedInUserResults


interface LoginRepository {

    var user: LoggedInUser?

    val isLoggedIn: Boolean
        get() = user != null

    @Throws(LoginException::class)
    suspend fun login(
        email: String,
        password: String
    ): LoggedInUserResults

    suspend fun saveLoggedUserInDb(loggedInUser: LoggedInUser)
    suspend fun saveTokenInDb(apiToken: String)
    suspend fun saveLoggedUserForAuth()

}
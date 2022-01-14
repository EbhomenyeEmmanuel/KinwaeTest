package com.example.kinwaetest.data

import com.example.kinwaetest.data.api.ApiParameters
import com.example.kinwaetest.data.api.KinswaeApi
import com.example.kinwaetest.data.api.model.LoginData
import com.example.kinwaetest.data.api.model.mappers.ApiLoginResultMapper
import com.example.kinwaetest.data.cache.Cache
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser
import com.example.kinwaetest.data.preferences.ApiPreferences
import com.example.kinwaetest.data.preferences.KinwaeAuthPreferences
import com.example.kinwaetest.domain.model.LoginException
import com.example.kinwaetest.domain.model.SignUpException
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import com.example.kinwaetest.domain.model.auth.LoggedInUserResults
import com.example.kinwaetest.domain.repositories.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: KinswaeApi,
    private val cache: Cache,
    private val apiPreferences: ApiPreferences,
    private val authPreferences: KinwaeAuthPreferences,
    private val apiLoginResultMapper: ApiLoginResultMapper
) : LoginRepository {

    override var user: LoggedInUser? = null

    override val isLoggedIn: Boolean
        get() = authPreferences.getUserLoginStatus()

    override suspend fun login(
        email: String,
        password: String
    ): LoggedInUserResults {
        val registerData = HashMap<String, String>()
        registerData[ApiParameters.EMAIL] = email
        registerData[ApiParameters.PASSWORD] = password
        val loginDataResponse: Response<LoginData> = api.login(registerData)
        if (!loginDataResponse.isSuccessful && loginDataResponse.body() == null || loginDataResponse.body()?.error != null) {
            throw LoginException(
                loginDataResponse.body()?.error ?: loginDataResponse.message()
                ?: "An error has occurred"
            )
        } else {
            return LoggedInUserResults(
                loggedInUser = apiLoginResultMapper.mapToDomain(loginDataResponse.body()?.result!!)
            )
        }
    }

    override suspend fun saveLoggedUserInDb(loggedInUser: LoggedInUser) {
        user = loggedInUser
        cache.storeLoggedInUser(CachedLoggedInUser.fromDomain(loggedInUser))
        authPreferences.saveUserFirstNameAsIdUsingSharedPref(loggedInUser.firstName)
    }

    override suspend fun saveTokenInDb(apiToken: String) {
        apiPreferences.putToken(apiToken)
    }

    override suspend fun saveLoggedUserForAuth() {
        authPreferences.saveUserLoginStatus(true)
    }
}
package com.example.kinwaetest.data

import com.example.kinwaetest.data.api.KinswaeApi
import com.example.kinwaetest.data.api.model.ApiSignUpData
import com.example.kinwaetest.data.api.model.mappers.ApiSignUpResultMapper
import com.example.kinwaetest.domain.model.SignUpException
import com.example.kinwaetest.domain.model.auth.SignedInUserResults
import com.example.kinwaetest.domain.repositories.SignUpRepository
import retrofit2.Response
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val api: KinswaeApi,
    private val apiSignUpResultMapper: ApiSignUpResultMapper
) : SignUpRepository {

    override suspend fun signUp(
        fullname: String,
        email: String,
        password: String
    ): SignedInUserResults {
        val registerData = HashMap<String, String>()
//        registerData[ApiParameters.EMAIL] = email
//        registerData[ApiParameters.PASSWORD] = password
//        registerData[ApiParameters.FULL_NAME] = fullname
        registerData["email"] = email
        registerData["password"] = password
        registerData["full_name"] = fullname

        val apiSignUpDataResponse: Response<ApiSignUpData> = api.register(registerData)
        if (!apiSignUpDataResponse.isSuccessful && apiSignUpDataResponse.body() == null || apiSignUpDataResponse.body()?.error != null) {
            throw SignUpException(
                 apiSignUpDataResponse.body()?.error?:apiSignUpDataResponse.message() ?:"An error has occurred"
            )
        } else {
            return apiSignUpResultMapper.mapToDomain(apiSignUpDataResponse.body()!!)
        }
    }

}
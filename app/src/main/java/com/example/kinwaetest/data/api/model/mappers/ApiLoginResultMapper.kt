package com.example.kinwaetest.data.api.model.mappers

import com.example.kinwaetest.data.api.model.ApiLoginResult
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import javax.inject.Inject

class ApiLoginResultMapper @Inject constructor() : ApiMapper<ApiLoginResult, LoggedInUser> {
    override fun mapToDomain(apiEntity: ApiLoginResult): LoggedInUser {
        return LoggedInUser(
            id = apiEntity.firstName + apiEntity.lastName,
            apiEntity.apiToken.accessToken ?: "",
            apiEntity.firstName,
            apiEntity.lastName,
            apiEntity.walletBalance
        )
    }
}
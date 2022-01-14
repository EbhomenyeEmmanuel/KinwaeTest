package com.example.kinwaetest.data.api.model.mappers

import com.example.kinwaetest.data.api.model.ApiSignUpData
import com.example.kinwaetest.domain.model.auth.SignedInUserResults
import javax.inject.Inject

class ApiSignUpResultMapper @Inject constructor() : ApiMapper<ApiSignUpData, SignedInUserResults> {
    override fun mapToDomain(apiEntity: ApiSignUpData): SignedInUserResults {
        return SignedInUserResults(
            successMessage = apiEntity.result!!,
        )
    }
}
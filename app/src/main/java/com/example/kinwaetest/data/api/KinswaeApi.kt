package com.example.kinwaetest.data.api

import com.example.kinwaetest.data.api.ApiConstants.API_CONTENT_TYPE
import com.example.kinwaetest.data.api.ApiParameters.END_DATE
import com.example.kinwaetest.data.api.ApiParameters.MAX
import com.example.kinwaetest.data.api.ApiParameters.NO_AUTH_HEADER
import com.example.kinwaetest.data.api.ApiParameters.PAGE
import com.example.kinwaetest.data.api.ApiParameters.START_DATE
import com.example.kinwaetest.data.api.model.ApiPaginatedTransactionsData
import com.example.kinwaetest.data.api.model.LoginData
import com.example.kinwaetest.data.api.model.ApiSignUpData
import retrofit2.Response
import retrofit2.http.*

interface KinswaeApi {

    @Headers(API_CONTENT_TYPE)
    @POST(ApiConstants.SIGN_UP_ENDPOINT)
    suspend fun register(
        @Body registerData: Map<String, String>
    ): Response<ApiSignUpData>

    @Headers(API_CONTENT_TYPE)
    @POST(ApiConstants.LOGIN_ENDPOINT)
    suspend fun login(
        @Body registerData: Map<String, String>
    ): Response<LoginData>

    @Headers(NO_AUTH_HEADER, API_CONTENT_TYPE)
    @FormUrlEncoded
    @POST(ApiConstants.TRANSACTION_LIST_ENDPOINT)
    suspend fun getTransactionDetailsList(
        @Field(START_DATE) startDate: Long = 1609459200000,
        @Field(END_DATE) endDate: Long = 1635638400000,
        @Field(PAGE) page: Int = 0,
        @Field(MAX) max: Int = 4,
    ): Response<ApiPaginatedTransactionsData>

}
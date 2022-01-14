package com.example.kinwaetest.data.api

object ApiConstants {
    const val BASE_ENDPOINT = "http://android-test.kinwae.com:8080"
    const val SIGN_UP_ENDPOINT = "/register"
    const val LOGIN_ENDPOINT = "/user/login"
    const val TRANSACTION_LIST_ENDPOINT = "/user/transactions"
    const val API_CONTENT_TYPE = "Content-Type: application/json"
}

object ApiParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
    const val NO_AUTH_HEADER = "no_auth"

    const val START_DATE = "start_date"
    const val END_DATE = "end_date"
    const val PAGE = "page"
    const val MAX = "max"

    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val FULL_NAME = "full_name"

}
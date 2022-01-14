package com.example.kinwaetest.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginData(
    @field:Json(name = "result") val result: ApiLoginResult? = null,
    val errorData: Any? = null,
    val error: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiLoginResult(
    @field:Json(name = "accessToken") val apiToken: ApiToken, val firstName: String, val lastName: String, val walletBalance: Int
)

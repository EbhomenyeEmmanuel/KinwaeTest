package com.example.kinwaetest.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSignUpData(
    @field:Json(name = "result") val result: String?,
    val error: String?
)

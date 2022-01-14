package com.example.kinwaetest.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiToken(
    val accessToken: String?
) {
    companion object {
        val INVALID = ApiToken("")
    }

    fun isValid(): Boolean {
        return accessToken != null && accessToken.isNotEmpty()
    }

}

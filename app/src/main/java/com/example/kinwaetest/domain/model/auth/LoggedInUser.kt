package com.example.kinwaetest.domain.model.auth

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val id: String,
    val accessToken: String,
    val firstName: String,
    val lastName: String,
    var walletBalance: Int,
)

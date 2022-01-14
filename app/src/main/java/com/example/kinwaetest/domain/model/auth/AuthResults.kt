package com.example.kinwaetest.domain.model.auth

data class LoggedInUserResults(
    val loggedInUser: LoggedInUser
)

data class SignedInUserResults(
    val successMessage: String
)
package com.example.kinwaetest.domain.model.auth

import javax.inject.Inject

/**
 * Data state of the sign up form.
 */
data class SignUpFormState @Inject constructor(
    var fullName: String,
    var userEmail: String,
    var phoneNumber: String,
    var password: String
)

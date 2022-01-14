package com.example.kinwaetest.domain.model.auth

import javax.inject.Inject

/**
 * Data state of the login form.
 */

data class LoginFormState @Inject constructor(var userEmail: String ,
                          var password: String)

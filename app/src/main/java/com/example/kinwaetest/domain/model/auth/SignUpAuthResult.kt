package com.example.kinwaetest.domain.model.auth

/**
 * Authentication result : success (user details) or error message.
 */
sealed class SignUpAuthResult{
        class Error(val error: String ): SignUpAuthResult()
        class Success( val successMessage: String): SignUpAuthResult()
        object Loading : SignUpAuthResult()
}
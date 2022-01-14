package com.example.kinwaetest.domain.model.auth

/**
 * Authentication result : success (user details) or error message.
 */
sealed class LogInAuthResult{
        class Error(val error: String ): LogInAuthResult()
        class Success( val success: LoggedInUserView? = null): LogInAuthResult()
        object Loading : LogInAuthResult()
}
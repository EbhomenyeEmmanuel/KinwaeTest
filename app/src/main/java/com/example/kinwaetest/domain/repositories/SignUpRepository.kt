package com.example.kinwaetest.domain.repositories

import com.example.kinwaetest.domain.model.SignUpException
import com.example.kinwaetest.domain.model.auth.SignedInUserResults
import kotlin.jvm.Throws

interface SignUpRepository {

    @Throws(SignUpException::class)
    suspend fun signUp(
        fullname: String,
        email: String,
        password: String
    ): SignedInUserResults
}
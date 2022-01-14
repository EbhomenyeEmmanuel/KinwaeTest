package com.example.kinwaetest.domain.model

import java.io.IOException

class SignUpException(message: String): Exception(message)
class LoginException(message: String): Exception(message)
class TransactionListException(message: String): Exception(message)

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)
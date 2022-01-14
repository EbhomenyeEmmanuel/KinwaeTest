package com.example.kinwaetest.data.preferences

interface ApiPreferences {

  fun putToken(token: String)

  fun getToken(): String

  fun deleteTokenInfo()
}
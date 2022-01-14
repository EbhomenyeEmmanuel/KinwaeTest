package com.example.kinwaetest.domain.repositories

import androidx.lifecycle.LiveData
import com.example.kinwaetest.domain.model.auth.LoggedInUser

interface MainRepository {
   suspend fun getUserFromDb(): LiveData<LoggedInUser>?
}
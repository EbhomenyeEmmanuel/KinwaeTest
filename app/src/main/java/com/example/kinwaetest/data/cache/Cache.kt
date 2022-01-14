package com.example.kinwaetest.data.cache

import androidx.lifecycle.LiveData
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser
import com.example.kinwaetest.data.cache.model.CachedTransaction
import com.example.kinwaetest.data.cache.model.CachedTransactionDetails
import kotlinx.coroutines.flow.Flow

interface Cache {
    suspend fun storeTransactionDetail(cachedTransactionDetails: CachedTransactionDetails)
    suspend fun storeTransactionDetailList(cachedTransactionDetails: List<CachedTransactionDetails>)
    fun getTransactionDetails(): Flow<List<CachedTransactionDetails>>
    fun getTransactionDetailsByReferenceNumber(refNumber: String): CachedTransactionDetails

    suspend fun storeTransactions(cachedTransactions: List<CachedTransaction>)
    fun getTransactions(): Flow<List<CachedTransaction>>

    suspend fun storeLoggedInUser(cachedLoggedInUser: CachedLoggedInUser)
    suspend fun updateLoggedInUser(cachedLoggedInUser: CachedLoggedInUser)
    fun getLoggedInUserWithFirstName(
        userFirstName: String): LiveData<CachedLoggedInUser>
    suspend fun deleteLoggedInUser()

}
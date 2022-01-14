package com.example.kinwaetest.data.cache

import androidx.lifecycle.LiveData
import com.example.kinwaetest.data.cache.daos.LoggedInUserDao
import com.example.kinwaetest.data.cache.daos.TransactionsDao
import com.example.kinwaetest.data.cache.daos.TransactionsDetailsDao
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser
import com.example.kinwaetest.data.cache.model.CachedTransaction
import com.example.kinwaetest.data.cache.model.CachedTransactionDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val transactionsDao: TransactionsDao,
    private val transactionsDetailsDao: TransactionsDetailsDao,
    private val loggedInUserDao: LoggedInUserDao
) : Cache {

    override suspend fun storeTransactionDetail(cachedTransactionDetails: CachedTransactionDetails) {
        transactionsDetailsDao.insertTransactionDetail(cachedTransactionDetails)
    }

    override suspend fun storeTransactionDetailList(cachedTransactionDetails: List<CachedTransactionDetails>) {
        transactionsDetailsDao.insertTransactionDetailsList(cachedTransactionDetails)
    }

    override fun getTransactionDetails(): Flow<List<CachedTransactionDetails>> {
        return transactionsDetailsDao.getTransactionDetails()
    }

    override fun getTransactionDetailsByReferenceNumber(refNumber: String): CachedTransactionDetails {
        return transactionsDetailsDao.getTransactionDetailsWithReferenceNumber(refNumber)
    }

    override suspend fun storeTransactions(cachedTransactions: List<CachedTransaction>) {
        transactionsDao.insertTransactions(cachedTransactions)
    }

    override fun getTransactions(): Flow<List<CachedTransaction>> {
        return transactionsDao.getTransactions()
    }

    override suspend fun storeLoggedInUser(cachedLoggedInUser: CachedLoggedInUser) {
        loggedInUserDao.insertLoggedInUser(cachedLoggedInUser)
    }

    override suspend fun updateLoggedInUser(cachedLoggedInUser: CachedLoggedInUser) {
        loggedInUserDao.insertLoggedInUser(cachedLoggedInUser)
    }

    override fun getLoggedInUserWithFirstName(userFirstName: String): LiveData<CachedLoggedInUser> {
        return loggedInUserDao.getLoggedInUserFromFirstName(
            userFirstName
        )
    }

    override suspend fun deleteLoggedInUser() {
        loggedInUserDao.deleteCachedUsers()
    }

}
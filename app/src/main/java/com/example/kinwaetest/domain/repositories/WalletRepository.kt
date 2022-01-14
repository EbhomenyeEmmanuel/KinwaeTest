package com.example.kinwaetest.domain.repositories

import com.example.kinwaetest.data.api.ApiParameters
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import com.example.kinwaetest.domain.model.pagination.PaginatedTransactions
import com.example.kinwaetest.domain.model.pagination.Pagination
import com.example.kinwaetest.domain.model.wallet.Transaction
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field

interface WalletRepository {

    suspend fun updateLoggedUserInDb(loggedInUser: LoggedInUser)

    suspend fun getTransactionsFromDb(): Flow<List<Transaction>>

    suspend fun requestMoreTransaction(startDate: Long, endDate: Long, pagination: Pagination): PaginatedTransactions?
    suspend fun storeTransactions(transaction: Transaction)

    suspend fun initTransactionDetails()

}
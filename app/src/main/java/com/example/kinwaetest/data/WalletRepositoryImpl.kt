package com.example.kinwaetest.data

import com.example.kinwaetest.data.api.ApiParameters
import com.example.kinwaetest.data.api.KinswaeApi
import com.example.kinwaetest.data.api.model.mappers.ApiPaginationMapper
import com.example.kinwaetest.data.api.model.mappers.ApiTransactionDetailsMapper
import com.example.kinwaetest.data.cache.Cache
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser
import com.example.kinwaetest.data.cache.model.CachedTransactionDetails
import com.example.kinwaetest.domain.model.auth.LoggedInUser
import com.example.kinwaetest.domain.model.pagination.PaginatedTransactions
import com.example.kinwaetest.domain.model.pagination.Pagination
import com.example.kinwaetest.domain.model.wallet.Transaction
import com.example.kinwaetest.domain.repositories.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val cache: Cache,
    private val api: KinswaeApi,
    private val apiTransactionDetailsMapper: ApiTransactionDetailsMapper,
    private val apiPaginationMapper: ApiPaginationMapper
) : WalletRepository {

    override suspend fun updateLoggedUserInDb(loggedInUser: LoggedInUser) {
        with(loggedInUser) {
            cache.updateLoggedInUser(
                CachedLoggedInUser(
                    id,
                    accessToken,
                    firstName,
                    lastName,
                    walletBalance
                )
            )
        }
    }

    override suspend fun getTransactionsFromDb(): Flow<List<Transaction>> {
        return cache.getTransactions()
            .map { it.map { cachedTransaction -> cachedTransaction.toDomain() } }
    }

    override suspend fun requestMoreTransaction(
        startDate: Long,
        endDate: Long,
        pagination: Pagination
    ): PaginatedTransactions? {
//        val dataFromApi = api.getTransactionDetailsList()
//        val pagination: Pagination =
//            apiPaginationMapper.mapToDomain(dataFromApi.result.apiPagination)
//        val paginatedTransactions: PaginatedTransactions = PaginatedTransactions(, pagination)
//      CachedTransactionDetails.fromDomain(dataFromApi.result.content)
//        cache.storeTransactionDetails(apiTransactionDetailsMapper.mapToDomain(dataFromApi.result.content))
        return null
    }

    override suspend fun storeTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun initTransactionDetails() {
        val dataFromApiResponse =
            api.getTransactionDetailsList(1609459200000, 1635638400000, 0, 4)
        if (dataFromApiResponse.isSuccessful) {
            val transactionDetailList =
                dataFromApiResponse.body()?.result?.content?.let {
                    apiTransactionDetailsMapper.mapToDomainList(
                        it
                    )
                }
            if (transactionDetailList != null) {
                cache.storeTransactionDetailList(transactionDetailList.map {
                    CachedTransactionDetails.fromDomain(
                        it
                    )
                })
            }
        }}
//
//    private getTransactionsDummyData(): List<Transaction> {
//        val transactionsDetailsList = cache.getTransactionDetails().toList()
//        val transactionList = listOf<Transaction>(
//            Transaction(0, 1, 2)
//        )
//    }
}
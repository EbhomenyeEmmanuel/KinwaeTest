package com.example.kinwaetest.data.api.model.mappers

import com.example.kinwaetest.data.api.model.ApiTransaction
import com.example.kinwaetest.domain.model.wallet.Transaction
import javax.inject.Inject

class ApiTransactionMapper @Inject constructor() :
    ApiMapper<ApiTransaction, Transaction> {
    override fun mapToDomain(apiEntity: ApiTransaction): Transaction {
        return Transaction(
            transactionDate = apiEntity.transactionDate,
            transactionAmount = apiEntity.transactionAmount,
            transactionTitle = apiEntity.transactionTitle,
            transactionTypeIcon = apiEntity.transactionTypeIcon,
            transactionId = apiEntity.transactionId,
            transactionDetails = apiEntity.transactionDetails
        )
    }

    fun mapToDomainList(apiTransactionList: List<ApiTransaction>): List<Transaction> {
        return apiTransactionList.map { mapToDomain(it) }
    }
}
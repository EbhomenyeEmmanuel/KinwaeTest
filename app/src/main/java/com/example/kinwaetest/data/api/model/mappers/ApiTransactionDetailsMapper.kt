package com.example.kinwaetest.data.api.model.mappers

import com.example.kinwaetest.data.api.model.ApiPaginatedTransactionsDetails
import com.example.kinwaetest.domain.model.wallet.TransactionDetails
import javax.inject.Inject

class ApiTransactionDetailsMapper @Inject constructor() :
    ApiMapper<ApiPaginatedTransactionsDetails, TransactionDetails> {
    override fun mapToDomain(apiEntity: ApiPaginatedTransactionsDetails): TransactionDetails {
        return TransactionDetails(
            transactionDate = covertToTransactionDate(apiEntity.transactionDate),
            transactionAmount = covertToTransactionAmount(apiEntity.transactionAmount),
            referenceNumber = apiEntity.referenceNumber,
            narration = apiEntity.narration
        )
    }

    private fun covertToTransactionDate(date: Int): String {
        return date.toString()
    }

    private fun covertToTransactionAmount(amount: Double): String {
        return amount.toString()
    }

    fun mapToDomainList(apiEntity: List<ApiPaginatedTransactionsDetails>): List<TransactionDetails> {
        return apiEntity.map { mapToDomain(it) }
    }
}
package com.example.kinwaetest.data.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.kinwaetest.domain.model.wallet.Transaction
import com.example.kinwaetest.domain.model.wallet.TransactionDetails

@Entity(
    tableName = "cached_transaction_details",
)
data class CachedTransactionDetails(
    val cachedTransactionDetailsDate: String,
    val cachedTransactionDetailsAmount: String,
    @PrimaryKey
    val cachedTransactionDetailsReferenceNumber: String,
    val cachedTransactionDetailsNarration: String
) {
    companion object {
        fun fromDomain(
            transactionDetails: TransactionDetails
        ): CachedTransactionDetails {
            return CachedTransactionDetails(
                transactionDetails.transactionDate,
                transactionDetails.transactionAmount,
                transactionDetails.referenceNumber,
                transactionDetails.narration
            )
        }
    }

    fun toDomain(): TransactionDetails = TransactionDetails(
        cachedTransactionDetailsDate,
        cachedTransactionDetailsAmount,
        cachedTransactionDetailsReferenceNumber,
        cachedTransactionDetailsNarration
    )
}
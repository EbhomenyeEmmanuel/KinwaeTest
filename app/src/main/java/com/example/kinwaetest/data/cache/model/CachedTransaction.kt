package com.example.kinwaetest.data.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kinwaetest.domain.model.wallet.Transaction

@Entity(
    tableName = "cached_transactions",
)
data class CachedTransaction(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long = 0,
    val transactionTypeIcon: Int,
    val transactionTitle: String,
    val transactionDate: String,
    val transactionAmount: String,
    @Embedded
    val cachedTransactionDetails: CachedTransactionDetails
) {
    companion object {
        fun fromDomain(
            transaction: Transaction
        ): CachedTransaction {
            return CachedTransaction(
                transaction.transactionId,
                transaction.transactionTypeIcon,
                transaction.transactionTitle,
                transaction.transactionDate,
                transaction.transactionAmount,
                CachedTransactionDetails.fromDomain(transaction.transactionDetails)
            )
        }
    }

    fun toDomain(): Transaction = Transaction(
        transactionId,
        transactionTypeIcon,
        transactionTitle,
        transactionDate,
        transactionAmount,
        cachedTransactionDetails.toDomain()
    )

}
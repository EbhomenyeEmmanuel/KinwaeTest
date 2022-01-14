package com.example.kinwaetest.data.api.model

import com.example.kinwaetest.domain.model.wallet.TransactionDetails

data class ApiTransaction(
    val transactionId: Long,
    val transactionTypeIcon: Int,
    val transactionTitle: String,
    val transactionDate: String,
    val transactionAmount: String,
    val transactionDetails: TransactionDetails
)
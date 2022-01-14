package com.example.kinwaetest.domain.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val transactionId: Long,
    val transactionTypeIcon: Int,
    val transactionTitle: String,
    val transactionDate: String,
    val transactionAmount: String,
    val transactionDetails: TransactionDetails
): Parcelable
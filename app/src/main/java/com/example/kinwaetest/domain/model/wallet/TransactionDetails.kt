package com.example.kinwaetest.domain.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionDetails(
    val transactionDate: String,
    val transactionAmount: String,
    val referenceNumber: String,
    val narration: String
): Parcelable
package com.example.kinwaetest.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPaginatedTransactionsData(
    val result: ApiPaginatedTransactionsDetailsResult,
    val errorData: String?,
    val error: String?,
)

@JsonClass(generateAdapter = true)
data class ApiPaginatedTransactionsDetailsResult(
    val content: List<ApiPaginatedTransactionsDetails>,
    val apiPagination: ApiPagination
)

@JsonClass(generateAdapter = true)
data class ApiPaginatedTransactionsDetails(
    @field:Json(name = "transaction_date") val transactionDate: Int,
    @field:Json(name = "transaction_amount") val transactionAmount: Double,
    @field:Json(name = "reference_number") val referenceNumber: String,
    val narration: String
)

@JsonClass(generateAdapter = true)
data class ApiPagination(
    val totalPages: Int,
    val totalItems: Int,
    val itemCount: Int,
    val page: Int
)
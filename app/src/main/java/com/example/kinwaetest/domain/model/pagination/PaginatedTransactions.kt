package com.example.kinwaetest.domain.model.pagination

import com.example.kinwaetest.domain.model.wallet.Transaction
import kotlinx.coroutines.flow.Flow

data class PaginatedTransactions (val transactions: Flow<List<Transaction>>, val pagination: Pagination)
package com.example.kinwaetest.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinwaetest.data.cache.model.CachedTransaction
import com.example.kinwaetest.data.cache.model.CachedTransactionDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TransactionsDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTransactionDetail(cachedTransactionDetails: CachedTransactionDetails)

    @Query("SELECT * FROM cached_transaction_details")
    abstract fun getTransactionDetails(): Flow<List<CachedTransactionDetails>>

    @Query("SELECT * FROM cached_transaction_details  WHERE cachedTransactionDetailsReferenceNumber= :referenceNumber")
    abstract fun getTransactionDetailsWithReferenceNumber(referenceNumber: String): CachedTransactionDetails

    @Query("DELETE FROM cached_transaction_details")
    abstract suspend fun deleteAllTransactionDetails()

    suspend fun insertTransactionDetailsList(
        transactionsDetails:
        List<CachedTransactionDetails>
    ) {
        for (transaction in transactionsDetails) {
            insertTransactionDetail(
                transaction
            )
        }
    }
}
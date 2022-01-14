package com.example.kinwaetest.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinwaetest.data.cache.model.CachedTransaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TransactionsDao {

    @Query("SELECT * FROM cached_transactions")
    abstract fun getTransactions(): Flow<List<CachedTransaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTransaction(
        transaction: CachedTransaction
    )

    suspend fun insertTransactions(
        transactions:
        List<CachedTransaction>
    ) {
        for (transaction in transactions) {
            insertTransaction(
                transaction
            )
        }
    }

}
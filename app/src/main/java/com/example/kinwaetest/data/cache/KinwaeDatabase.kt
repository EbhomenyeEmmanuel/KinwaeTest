package com.example.kinwaetest.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinwaetest.data.cache.daos.LoggedInUserDao
import com.example.kinwaetest.data.cache.daos.TransactionsDao
import com.example.kinwaetest.data.cache.daos.TransactionsDetailsDao
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser
import com.example.kinwaetest.data.cache.model.CachedTransaction
import com.example.kinwaetest.data.cache.model.CachedTransactionDetails

@Database(
    entities = [
        CachedTransaction::class,
        CachedTransactionDetails::class,
        CachedLoggedInUser::class
    ],
    version = 1, exportSchema = false
)
abstract class KinwaeDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionsDao
    abstract fun transactionDetailsDao(): TransactionsDetailsDao
    abstract fun loggedInUserDao(): LoggedInUserDao
}
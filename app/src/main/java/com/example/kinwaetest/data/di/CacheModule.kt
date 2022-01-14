package com.example.kinwaetest.data.di

import android.content.Context
import androidx.room.Room
import com.example.kinwaetest.data.cache.Cache
import com.example.kinwaetest.data.cache.KinwaeDatabase
import com.example.kinwaetest.data.cache.RoomCache
import com.example.kinwaetest.data.cache.daos.LoggedInUserDao
import com.example.kinwaetest.data.cache.daos.TransactionsDao
import com.example.kinwaetest.data.cache.daos.TransactionsDetailsDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton // 1
        fun provideDatabase(
            @ApplicationContext context: Context // 2
        ): KinwaeDatabase {
            return Room.databaseBuilder( // 3
                context,
                KinwaeDatabase::class.java,
                "kinwae.db"
            ).build()
        }

        @Provides
        fun provideTransactionDao(
            kinwaeDatabase: KinwaeDatabase
        ): TransactionsDao = kinwaeDatabase.transactionDao()


        @Provides
        fun provideTransactionDetailsDao(
            kinwaeDatabase: KinwaeDatabase
        ): TransactionsDetailsDao = kinwaeDatabase.transactionDetailsDao()

        @Provides
        fun provideLoggedInUserDao(
            kinwaeDatabase: KinwaeDatabase
        ): LoggedInUserDao = kinwaeDatabase.loggedInUserDao()


    }
}
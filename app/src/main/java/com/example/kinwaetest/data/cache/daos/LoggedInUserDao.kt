package com.example.kinwaetest.data.cache.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinwaetest.data.cache.model.CachedLoggedInUser

@Dao
abstract class LoggedInUserDao {

    @Query("SELECT * FROM cached_logged_user WHERE firstName = :userId")
    abstract fun getLoggedInUserFromFirstName(userId: String): LiveData<CachedLoggedInUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertLoggedInUser(
        loggedInUser: CachedLoggedInUser
    )

    @Query("DELETE FROM cached_logged_user")
    abstract suspend fun deleteCachedUsers()

}
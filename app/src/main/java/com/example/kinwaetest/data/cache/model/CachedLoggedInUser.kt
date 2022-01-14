package com.example.kinwaetest.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kinwaetest.domain.model.auth.LoggedInUser


@Entity(
    tableName = "cached_logged_user",
)
data class CachedLoggedInUser(
    @PrimaryKey
    val id: String,
    val accessToken: String,
    val firstName: String,
    val lastName: String,
    var walletBalance: Int
) {
    companion object {
        fun fromDomain(loggedInUser: LoggedInUser): CachedLoggedInUser {
            return CachedLoggedInUser(
                loggedInUser.id,
                loggedInUser.accessToken,
                loggedInUser.firstName,
                loggedInUser.lastName,
                loggedInUser.walletBalance
            )
        }
    }

    fun toDomain(): LoggedInUser {
        return LoggedInUser(id, accessToken, firstName, lastName, walletBalance)
    }
}
package com.example.habitapptracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habitapptracker.model.User

// *** NEW: DAO untuk operasi database User ***
@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    // *** NEW: Query login - cari user berdasarkan username dan password ***
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int
}
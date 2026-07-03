package com.example.habitapptracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habitapptracker.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int
}
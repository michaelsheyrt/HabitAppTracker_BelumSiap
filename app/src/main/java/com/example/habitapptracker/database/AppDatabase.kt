package com.example.habitapptracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.habitapptracker.model.Habit
import com.example.habitapptracker.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// *** NEW: AppDatabase dengan singleton pattern + seed user default ***
@Database(entities = [User::class, Habit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "habit_database"
                ).addCallback(object : Callback() {
                    // *** CHANGED: Seed 1 user default via DAO insert + Coroutine (pola sesuai materi Week 11) ***
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            INSTANCE?.userDao()?.insert(User(username = "student", password = "123"))
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
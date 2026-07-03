package com.example.habitapptracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class  Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // *** NEW ***
    val name: String,
    val description: String,
    val goal: Int,
    val unit: String,
    val icon: String,
    var currentProgress: Int = 0
)
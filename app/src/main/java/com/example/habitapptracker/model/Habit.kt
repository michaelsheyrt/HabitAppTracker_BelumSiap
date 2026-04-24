package com.example.habitapptracker.model

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    var progress: Int = 0,
    val unit: String,
    val icon: String
)
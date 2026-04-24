package com.example.habitapptracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitapptracker.data.HabitRepository
import com.example.habitapptracker.model.Habit

class HabitViewModel : ViewModel() {

    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits

    init {
        _habits.value = HabitRepository.habitList
    }

    fun addHabit(habit: Habit) {
        HabitRepository.habitList.add(habit)
        _habits.value = HabitRepository.habitList
    }

    fun updateProgress(position: Int, value: Int) {
        val habit = HabitRepository.habitList[position]

        habit.progress += value

        if (habit.progress < 0) habit.progress = 0
        if (habit.progress > habit.goal) habit.progress = habit.goal

        _habits.value = HabitRepository.habitList
    }
}
package com.example.habitapptracker.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitapptracker.model.Habit

class HabitViewModel : ViewModel() {
    private val habitList = mutableListOf<Habit>()
    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits

    init {
        _habits.value = habitList
    }

    fun addHabit(habit: Habit) {
        habitList.add(habit)
        _habits.value = habitList.toList()
    }

    fun updateProgress(position: Int, value: Int) {
        val habit = habitList[position]
        habit.progress += value
        if (habit.progress < 0) habit.progress = 0
        if (habit.progress > habit.goal) habit.progress = habit.goal
        _habits.value = habitList.toList()
    }
}
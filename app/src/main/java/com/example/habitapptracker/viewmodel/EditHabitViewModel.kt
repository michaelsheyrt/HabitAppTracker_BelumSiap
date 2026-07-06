package com.example.habitapptracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habitapptracker.database.AppDatabase
import kotlinx.coroutines.launch

class EditHabitViewModel(application: Application) : AndroidViewModel(application) {
    private val habitDao = AppDatabase.getInstance(application).habitDao()
    val habitName = MutableLiveData<String>()
    val habitDescription = MutableLiveData<String>()
    val habitGoal = MutableLiveData<String>()
    val habitUnit = MutableLiveData<String>()
    val habitIcon = MutableLiveData<String>()

    private var habitId: Int = 0

    fun loadHabit(id: Int) {
        habitId = id
        viewModelScope.launch {
            val habit = habitDao.getById(id) ?: return@launch
            habitName.postValue(habit.name)
            habitDescription.postValue(habit.description)
            habitGoal.postValue(habit.goal.toString())
            habitUnit.postValue(habit.unit)
            habitIcon.postValue(habit.icon)
        }
    }

    fun updateHabit(onSuccess: () -> Unit) {
        val name = habitName.value?.takeIf { it.isNotBlank() } ?: return
        val description = habitDescription.value ?: return
        val goal = habitGoal.value?.toIntOrNull() ?: return
        val unit = habitUnit.value?.takeIf { it.isNotBlank() } ?: return
        val icon = habitIcon.value ?: "Water"

        viewModelScope.launch {
            val current = habitDao.getById(habitId) ?: return@launch
            val updated = current.copy(
                name = name,
                description = description,
                goal = goal,
                unit = unit,
                icon = icon
            )
            habitDao.update(updated)
            onSuccess()
        }
    }
}
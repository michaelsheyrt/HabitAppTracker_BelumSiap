package com.example.habitapptracker.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habitapptracker.database.AppDatabase
import com.example.habitapptracker.model.Habit
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val habitDao = AppDatabase.getInstance(application).habitDao()

    val habits: LiveData<List<Habit>> = habitDao.getAll()

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitDao.insert(habit)
        }
    }

    fun incrementProgress(habit: Habit) {
        viewModelScope.launch {
            val updated = habit.copy(currentProgress = minOf(habit.currentProgress + 1, habit.goal))
            habitDao.update(updated)
        }
    }

    fun decrementProgress(habit: Habit) {
        viewModelScope.launch {
            val updated = habit.copy(currentProgress = maxOf(habit.currentProgress - 1, 0))
            habitDao.update(updated)
        }
    }
}
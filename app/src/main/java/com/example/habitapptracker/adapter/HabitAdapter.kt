package com.example.habitapptracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitapptracker.databinding.ItemHabitBinding  // *** NEW: DataBinding ***
import com.example.habitapptracker.model.Habit
import com.example.habitapptracker.viewmodel.HabitViewModel

class HabitAdapter(
    private val viewModel: HabitViewModel,
    private val onHabitClick: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    var list: List<Habit> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = list[position]
        holder.binding.habit = habit
        holder.binding.viewModel = viewModel
        holder.binding.executePendingBindings()

        holder.binding.txtName.setOnClickListener {
            onHabitClick(habit)
        }
    }
}

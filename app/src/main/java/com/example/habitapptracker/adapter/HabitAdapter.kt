package com.example.habitapptracker.adapter

import android.graphics.Color
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.habitapptracker.R
import com.example.habitapptracker.model.Habit

class HabitAdapter(
    val list: MutableList<Habit>,
    private val onUpdate: (Int, Int) -> Unit
) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.txtName)
        val desc: TextView = v.findViewById(R.id.txtDesc)
        val progress: ProgressBar = v.findViewById(R.id.progressBar)
        val status: TextView = v.findViewById(R.id.txtStatus)
        val txtProgress: TextView = v.findViewById(R.id.txtProgress)

        val plus: TextView = v.findViewById(R.id.btnPlus)
        val minus: TextView = v.findViewById(R.id.btnMinus)

        val icon: ImageView = v.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = list[position]

        holder.name.text = habit.name
        holder.desc.text = habit.description

        val percent = if (habit.goal == 0) 0 else (habit.progress * 100) / habit.goal
        holder.progress.progress = percent

        holder.txtProgress.text = "${habit.progress} / ${habit.goal} ${habit.unit}"

        if (habit.progress >= habit.goal) {
            holder.status.text = "Completed"
            holder.status.setBackgroundColor(Color.parseColor("#4CAF50"))
        } else {
            holder.status.text = "In Progress"
            holder.status.setBackgroundColor(Color.parseColor("#6A00F4"))
        }

        holder.status.setTextColor(Color.WHITE)
        holder.status.setPadding(10, 4, 10, 4)

        when (habit.icon) {
            "Water" -> holder.icon.setImageResource(R.drawable.ic_water)
            "Fitness" -> holder.icon.setImageResource(R.drawable.ic_fitness)
            "Book" -> holder.icon.setImageResource(R.drawable.ic_book)
            "Meditation" -> holder.icon.setImageResource(R.drawable.ic_meditation)
            else -> holder.icon.setImageResource(R.drawable.ic_launcher_foreground)
        }

        holder.plus.setOnClickListener {
            onUpdate(position, 1)
        }

        holder.minus.setOnClickListener {
            onUpdate(position, -1)
        }
    }
}
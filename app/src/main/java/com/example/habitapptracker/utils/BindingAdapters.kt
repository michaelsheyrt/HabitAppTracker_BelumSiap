package com.example.habitapptracker.utils

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.habitapptracker.R
import com.example.habitapptracker.model.Habit

@BindingAdapter("habitIcon")
fun ImageView.setHabitIcon(icon: String?) {
    when (icon) {
        "Water" -> setImageResource(R.drawable.ic_water)
        "Fitness" -> setImageResource(R.drawable.ic_fitness)
        "Book" -> setImageResource(R.drawable.ic_book)
        "Meditation" -> setImageResource(R.drawable.ic_meditation)
        else -> setImageResource(R.drawable.ic_launcher_foreground)
    }
}

@BindingAdapter("statusBackground")
fun View.setStatusBackground(isCompleted: Boolean) {
    setBackgroundColor(
        if (isCompleted) Color.parseColor("#4CAF50")
        else Color.parseColor("#6A00F4")
    )
}

@BindingAdapter("progressText")
fun TextView.setProgressText(habit: Habit?){
habit?.let{text= "${it.currentProgress}/${it.goal} ${it.unit}"}
}

@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(value: String?) {
    val spinnerAdapter = adapter ?: return
    val pos = (0 until spinnerAdapter.count)
        .firstOrNull { spinnerAdapter.getItem(it) == value } ?: return
    if (selectedItemPosition != pos) setSelection(pos)
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): String? = selectedItem as? String

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setSelectedValueAttrChanged(listener: InverseBindingListener) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listener.onChange()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}
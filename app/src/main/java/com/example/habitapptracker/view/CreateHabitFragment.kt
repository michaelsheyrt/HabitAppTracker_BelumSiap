package com.example.habitapptracker.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitapptracker.R
import com.example.habitapptracker.model.Habit
import com.example.habitapptracker.viewmodel.HabitViewModel

class CreateHabitFragment : Fragment(R.layout.fragment_create_habit) {

    private lateinit var viewModel: HabitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        val name = view.findViewById<EditText>(R.id.etName)
        val desc = view.findViewById<EditText>(R.id.etDesc)
        val goal = view.findViewById<EditText>(R.id.etGoal)
        val unit = view.findViewById<EditText>(R.id.etUnit)
        val spinner = view.findViewById<Spinner>(R.id.spIcon)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val icons = listOf("Water", "Fitness", "Book", "Meditation")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, icons)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnSave.setOnClickListener {

            if (name.text.isEmpty() || desc.text.isEmpty() ||
                goal.text.isEmpty() || unit.text.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Isi semua field!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val habit = Habit(
                name.text.toString(),
                desc.text.toString(),
                goal.text.toString().toInt(),
                0,
                unit.text.toString(),
                spinner.selectedItem.toString()
            )

            viewModel.addHabit(habit)

            Toast.makeText(requireContext(), "Habit ditambahkan", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }
    }
}
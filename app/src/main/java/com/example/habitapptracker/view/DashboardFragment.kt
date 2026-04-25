package com.example.habitapptracker.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitapptracker.R
import com.example.habitapptracker.adapter.HabitAdapter
import com.example.habitapptracker.model.Habit
import com.example.habitapptracker.viewmodel.HabitViewModel

class DashboardFragment: Fragment(R.layout.fragment_dashboard)  {
    private lateinit var viewModel: HabitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerHabit)
        val fab = view.findViewById<View>(R.id.fabAdd)
        val adapter = HabitAdapter(mutableListOf()){position, value ->
            viewModel.updateProgress(position, value)
        }

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.habits.observe(viewLifecycleOwner){
            adapter.list.clear()
            adapter.list.addAll(it)
            adapter.notifyDataSetChanged()
        }

        if(viewModel.habits.value.isNullOrEmpty()){
            viewModel.addHabit(
                Habit("Minum Air", "Minum 8 gelas", 8, 0, "gelas", "Water")
            )
            viewModel.addHabit(
                Habit("Olahraga", "30 menit olahraga", 30, 0, "menitt", "Fitness")
            )
        }

        fab.setOnClickListener{
            findNavController().navigate(R.id.action_dashboard_to_create)
        }
    }
}
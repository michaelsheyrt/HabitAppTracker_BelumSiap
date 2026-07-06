package com.example.habitapptracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.habitapptracker.databinding.FragmentDashboardBinding
import com.example.habitapptracker.model.Habit
import com.example.habitapptracker.viewmodel.HabitViewModel

class DashboardFragment: Fragment()  {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

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

        fab.setOnClickListener{
            findNavController().navigate(R.id.action_dashboard_to_create)
        }
    }
}
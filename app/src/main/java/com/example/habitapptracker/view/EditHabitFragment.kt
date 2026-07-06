package com.example.habitapptracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitapptracker.R
import com.example.habitapptracker.databinding.FragmentEditHabitBinding
import com.example.habitapptracker.viewmodel.EditHabitViewModel

class EditHabitFragment : Fragment() {

    private var _binding: FragmentEditHabitBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditHabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EditHabitViewModel::class.java]

        val icons =listOf("Water", "Fitness", "Book", "Meditation")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, icons)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spIcon.adapter = spinnerAdapter

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val habitId = arguments?.getInt("habitId") ?: -1
        viewModel.loadHabit(habitId)

        binding.btnSubmit.setOnClickListener {
            viewModel.updateHabit {
                Toast.makeText(requireContext(), "Habit diperbarui", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
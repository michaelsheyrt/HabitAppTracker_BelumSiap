package com.example.habitapptracker.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habitapptracker.R
import com.example.habitapptracker.database.AppDatabase
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("HabitTracker", Context.MODE_PRIVATE)
        if (prefs.getBoolean("is_logged_in", false)) {
            findNavController().navigate(R.id.action_login_to_dashboard)
            return
        }

        val etUsername = view.findViewById<EditText>(R.id.etUsername)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        val userDao = AppDatabase.getInstance(requireContext()).userDao()

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Username dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val user = userDao.login(username, password)
                    if (user != null) {
                        prefs.edit().putBoolean("is_logged_in", true).apply()
                        findNavController().navigate(R.id.action_login_to_dashboard)
                    } else {
                        Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "DB Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
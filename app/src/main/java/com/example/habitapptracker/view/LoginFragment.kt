package com.example.habitapp.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.habitapptracker.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val username = view.findViewById<EditText>(R.id.etUsername)
        val password = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            if (username.text.toString() == "student" &&
                password.text.toString() == "123"
            ) {
                findNavController().navigate(R.id.action_login_to_dashboard)
            } else {
                Toast.makeText(requireContext(), "Login gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
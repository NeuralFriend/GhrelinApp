package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.pavelt.ghrelin.R

class FragmentAuthorization : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)
        val btnJoin = view.findViewById<Button>(R.id.btnEnter)
        val etLogin = view.findViewById<EditText>(R.id.etLogin)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)

        btnJoin.setOnClickListener {
            if (etLogin.text.toString() == "login" && etPassword.text.toString() == "password") {
                findNavController().navigate(R.id.fragmentCatalog, null)
            }
            else{
                Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

}
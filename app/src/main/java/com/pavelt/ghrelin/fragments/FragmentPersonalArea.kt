package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.userRepository.UserRepositoryImpl

class FragmentPersonalArea : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal_area, container, false)
        val tvUserName = view.findViewById<TextView>(R.id.userName)
        val tvRole = view.findViewById<TextView>(R.id.userRole)
        val btnLogOut = view.findViewById<Button>(R.id.btnLogOut)

        val rep = UserRepositoryImpl()
        val user = arguments?.getInt("id")
        val userRole = rep.data.find { it.id == user }

        tvUserName.text = userRole?.firstName
        tvRole.text = userRole?.lastName

        btnLogOut.setOnClickListener {
            findNavController().navigate(R.id.fragmentAuthorization)
        }


        return view

    }
}
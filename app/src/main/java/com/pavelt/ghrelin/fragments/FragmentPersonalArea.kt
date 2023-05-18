package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.data.AppStateRepository
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            val user = AppStateRepository.get().currUser
            tvUserName.text = user?.firstName
            tvRole.text = user?.lastName
        }

        btnLogOut.setOnClickListener {
            findNavController().navigate(R.id.fragmentAuthorization)
        }


        return view

    }
}
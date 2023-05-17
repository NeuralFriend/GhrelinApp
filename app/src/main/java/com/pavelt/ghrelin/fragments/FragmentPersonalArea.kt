package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.pavelt.ghrelin.R

class FragmentPersonalArea : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal_area, container, false)

        val btnLogOut = view.findViewById<Button>(R.id.btnLogOut)
        btnLogOut.setOnClickListener {
            findNavController().navigate(R.id.fragmentAuthorization)
        }


        return view

    }
}
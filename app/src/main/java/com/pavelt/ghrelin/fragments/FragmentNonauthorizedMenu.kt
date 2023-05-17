package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.CustomRecyclerAdapter
import com.pavelt.ghrelin.classes.menuRecyclerView.FoodRepositoryImpl
import com.pavelt.ghrelin.classes.menuRecyclerView.OnItemClickListener

class FragmentNonauthorizedMenu : Fragment(), OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nonauthorized_menu, container, false)

        val rep = FoodRepositoryImpl()

        val recyclerView: RecyclerView = view.findViewById(R.id.rvMenuNonAuth)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomRecyclerAdapter(rep.data, this)

        val btnLogin = view.findViewById<Button>(R.id.btnJoinFromRv)

        btnLogin.setOnClickListener { findNavController().navigate(R.id.fragmentAuthorization) }

        return view
    }

    override fun onItemClicked(position: Int, id: Int) {
        Toast.makeText(requireContext(), "Для заказа нужно авторизоваться в приложении", Toast.LENGTH_SHORT).show()
    }
}
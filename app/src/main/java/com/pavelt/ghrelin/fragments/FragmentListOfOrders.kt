package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.listOfOrdersRecyclerView.ListOfOrdersAdapter
import com.pavelt.ghrelin.classes.menuRecyclerView.OnItemClickListener
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.domain.AppState
import kotlinx.coroutines.launch

class FragmentListOfOrders : Fragment(), OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_orders, container, false)

        lifecycleScope.launch {
            val listOfOrders = AppStateRepository.get().orders

            val recyclerView: RecyclerView = view.findViewById(R.id.rvListOfOrders)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val adapter = ListOfOrdersAdapter(listOfOrders.toList(), object : OnItemClickListener {

                override fun onItemClicked(position: Int, id: String) {
                    val bundle = bundleOf("id" to id)
                    findNavController().navigate(R.id.fragmentListOfOrdersDetail, bundle)
                }
            })
            recyclerView.adapter = adapter
        }

        return view
    }

    override fun onItemClicked(position: Int, id: String) {

    }


}
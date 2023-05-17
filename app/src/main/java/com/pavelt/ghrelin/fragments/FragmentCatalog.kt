package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.CustomRecyclerAdapter
import com.pavelt.ghrelin.classes.menuRecyclerView.FoodRepositoryImpl
import com.pavelt.ghrelin.classes.menuRecyclerView.OnItemClickListener

class FragmentCatalog : Fragment(), OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val rep = FoodRepositoryImpl()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomRecyclerAdapter(rep.data, this)

        return view
    }

    override fun onItemClicked(position: Int, id: Int) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.fragmentFoodDetail, bundle)
    }
}
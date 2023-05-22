package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.CustomRecyclerAdapter
import com.pavelt.ghrelin.classes.menuRecyclerView.OnItemClickListener
import com.pavelt.ghrelin.domain.FoodMock

class FragmentCatalog : Fragment(), OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomRecyclerAdapter(FoodMock.data, this)

        return view
    }

    override fun onItemClicked(position: Int, id: String) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.fragmentFoodDetail, bundle)
    }
}
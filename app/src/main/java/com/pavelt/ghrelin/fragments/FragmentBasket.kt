package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.FoodRepositoryImpl

class FragmentBasket : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_basket, container, false)

        val tvTitle = view.findViewById<TextView>(R.id.foodTitleBasket)
        val tvWeight = view.findViewById<TextView>(R.id.foodPriceBasket)
        val foodImg = view.findViewById<ImageView>(R.id.foodImageBasket)
//        val btnOrder = view.findViewById<Button>(R.id.BtnOrder)
        val repository = FoodRepositoryImpl()
        val id = arguments?.getInt("id")
        val dataById = id?.let { repository.getDataById(it) }

        tvTitle.text = dataById?.name
        tvWeight.text = dataById?.price.toString()
        Glide.with(requireContext())
            .load(dataById?.image)
            .into(foodImg)

        return view
    }
}
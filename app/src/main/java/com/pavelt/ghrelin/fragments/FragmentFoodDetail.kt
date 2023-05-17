package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.FoodRepositoryImpl

class FragmentFoodDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_detail, container, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvWeight = view.findViewById<TextView>(R.id.tvWeight)
        val foodImg = view.findViewById<ImageView>(R.id.foodImageView)
        val btnOrder = view.findViewById<Button>(R.id.BtnOrder)
        val btnBuy = view.findViewById<Button>(R.id.btnBuy)
        val repository = FoodRepositoryImpl()
        val id = arguments?.getInt("id")
        val dataById = id?.let { repository.getDataById(it) }

        tvTitle.text = dataById?.name
        tvWeight.text = dataById?.weight
        Glide.with(requireContext())
            .load(dataById?.image)
            .into(foodImg)

        val bundle = bundleOf("id" to dataById?.id)

        btnOrder.setOnClickListener {
            findNavController().navigate(R.id.fragmentBasket, bundle)
            //findNavController().navigate(R.id.fragmentCatalog)
            Toast.makeText(
                requireContext(),
                "Заказ добавлен в корзину",
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
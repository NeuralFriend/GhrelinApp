package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.databinding.FragmentFoodDetailBinding
import com.pavelt.ghrelin.domain.Food
import com.pavelt.ghrelin.domain.FoodMock
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FragmentFoodDetail : Fragment(R.layout.fragment_food_detail) {

    private val binding by viewBinding(FragmentFoodDetailBinding::bind)

    private val foodId: Int
        get() = arguments?.getInt("id")!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        super.onViewCreated(view, savedInstanceState)

        val food = FoodMock.getFoodById(foodId)!!
        tvTitle.text = food.name
        tvWeight.text = food.weight
        Glide.with(requireContext())
            .load(food.imageUrl)
            .into(foodImageView)

        btnOrder.setOnClickListener { addFoodToCard(food) }

        AppStateRepository
            .observe()
            .onEach {
                if (it.cart.hasItem(foodId)) {
                    btnOrder.text = "Добавлено"
                    btnOrder.isEnabled = false
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun addFoodToCard(food: Food) {
        lifecycleScope.launch {
            AppStateRepository.update { oldAppState ->
                oldAppState.copy(cart = oldAppState.cart.withNewItem(food))
            }
        }
        Toast.makeText(
            requireContext(),
            "Блюдо добавлено в корзину",
            Toast.LENGTH_SHORT
        ).show()
    }
}
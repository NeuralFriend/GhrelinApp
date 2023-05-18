package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.databinding.FragmentBasketBinding
import com.pavelt.ghrelin.domain.Order
import kotlinx.coroutines.launch

class FragmentBasket : Fragment(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val cartItems = AppStateRepository.get().cart.items
            // todo render items list
        }
        // todo table number input
        btnCreateOrder.setOnClickListener { createOrder(tableNumber = 0) }
    }

    private fun createOrder(tableNumber: Int) = lifecycleScope.launch {
        AppStateRepository.update { oldAppState ->
            val items = oldAppState.cart.items
            val newOrder = Order(
                items = items,
                tableNumber = tableNumber,
            )
            oldAppState
                .withNewOrder(newOrder)
                .withClearCart()
        }
    }
}
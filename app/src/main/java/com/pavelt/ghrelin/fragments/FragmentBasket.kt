package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Strings.isNullOrEmpty
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.basketRecyclerView.BasketRecyclerAdapter
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.databinding.FragmentBasketBinding
import com.pavelt.ghrelin.domain.Order
import io.ktor.utils.io.*
import kotlinx.coroutines.launch
import javax.xml.parsers.FactoryConfigurationError

class FragmentBasket : Fragment(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBasket)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val cartItems = AppStateRepository.get().cart.items
            recyclerView.adapter = BasketRecyclerAdapter(cartItems.toList())
        }

        btnCreateOrder.isEnabled = false

        tableNumber.doOnTextChanged { text, start, before, count ->
            btnCreateOrder.isEnabled = !text.isNullOrEmpty()
        }

        btnCreateOrder.setOnClickListener {
            createOrder(tableNumber = tableNumber.text.toString().toInt())
            Toast.makeText(
                requireContext(),
                "Заказ успешно создан. Номер вашего стола - ${tableNumber.text}",
                Toast.LENGTH_LONG
            ).show()
            tableNumber.text.clear()
            lifecycleScope.launch {
                val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewBasket)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                val cartItems = AppStateRepository.get().cart.items
                recyclerView.adapter = BasketRecyclerAdapter(cartItems.toList())
            }
        }
    }

    private fun createOrder(tableNumber: Int) = lifecycleScope.launch {
        try {
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
        } catch (ex: Exception) {
            Toast.makeText(requireContext(), "Заказ не может быть пустым", Toast.LENGTH_SHORT)
                .show()
        }

    }
}
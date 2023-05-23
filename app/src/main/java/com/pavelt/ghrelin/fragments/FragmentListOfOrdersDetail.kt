package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.system.Os.bind
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.databinding.FragmentListOfOrdersDetailBinding
import com.pavelt.ghrelin.domain.OrderStatus
import com.pavelt.ghrelin.domain.UserRole
import kotlinx.coroutines.launch

class FragmentListOfOrdersDetail : Fragment(R.layout.fragment_list_of_orders_detail) {

    private val binding by viewBinding(FragmentListOfOrdersDetailBinding::bind)

    private val foodId: String
        get() = arguments?.getString("id")!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        super.onViewCreated(view, savedInstanceState)

        val btnWaiterAccept = btnAcceptWaiter.apply { isVisible = false }
        val btnCookAccept = btnAcceptCook.apply { isVisible = false }
        val btnOrderDoneCook = btnOrderDoneCook.apply {
            isVisible = false
            isEnabled = false
        }
        val btnOrderDoneWaiter = btnOrderDoneWaiter.apply {
            isVisible = false
            isEnabled = false
        }

        lifecycleScope.launch {
            val orderId = foodId
            val orderStatus = AppStateRepository.get().orders
            orderStatus.map { order ->
                if (order.id == orderId) {
                    btnCookAccept.isEnabled = order.status != OrderStatus.COOKING
                    btnOrderDoneCook.isEnabled = order.status != OrderStatus.CREATED
                    tvTableTitle.text = "Номер стола: ${order.tableNumber.toString()}"
                    val foodItems = order.items.toList()
                    Glide.with(requireContext())
                        .load(foodItems.joinToString { food -> food.imageUrl })
                        .into(imageOfOrder)
                    tvFoodTitle.text = foodItems.joinToString { food -> food.name }
                    val totalCost = foodItems.sumOf { food -> food.price }
                    tvPriceTitle.text = "Стоимость: $totalCost"
                }

            }
        }

        lifecycleScope.launch {
            val user = AppStateRepository.get().currUser

            if (user!!.role == UserRole.WAITER) {
                btnWaiterAccept.isVisible = true
                btnOrderDoneWaiter.isVisible = true
            } else if (user.role == UserRole.COOK) {
                btnCookAccept.isVisible = true
                btnOrderDoneCook.isVisible = true
            }
        }

        lifecycleScope.launch {
            val orderId = foodId
            AppStateRepository.observe().collect { appState ->
                val order = appState.orders.find { it.id == orderId }
                btnWaiterAccept.isEnabled = order!!.status == OrderStatus.COOKED
                btnOrderDoneWaiter.isEnabled = order.status == OrderStatus.DELIVERING
                btnCookAccept.isEnabled = order.status == OrderStatus.CREATED
                btnOrderDoneCook.isEnabled = order.status == OrderStatus.COOKING
            }
        }

        btnCookAccept.setOnClickListener {
            lifecycleScope.launch {
                val orderId = foodId
                AppStateRepository.update { appState ->
                    val updatedOrders = appState.orders.map { order ->
                        if (order.id == orderId) {
                            order.copy(status = OrderStatus.COOKING)
                        } else {
                            order
                        }
                    }
                    appState.copy(orders = updatedOrders.toSet())
                }
            }
            btnCookAccept.isEnabled = false
            btnOrderDoneCook.isEnabled = true
        }

        btnOrderDoneCook.setOnClickListener {
            lifecycleScope.launch {
                val orderId = foodId
                AppStateRepository.update { appState ->
                    val updatedOrders = appState.orders.map { order ->
                        if (order.id == orderId) {
                            order.copy(status = OrderStatus.COOKED)
                        } else {
                            order
                        }
                    }
                    appState.copy(orders = updatedOrders.toSet())
                }
            }
        }

        btnWaiterAccept.setOnClickListener {
            lifecycleScope.launch {
                val orderId = foodId
                AppStateRepository.update { appState ->
                    val updatedOrders = appState.orders.map { order ->
                        if (order.id == orderId) {
                            order.copy(status = OrderStatus.DELIVERING)
                        } else {
                            order
                        }
                    }
                    appState.copy(orders = updatedOrders.toSet())
                }
            }
        }

        btnOrderDoneWaiter.setOnClickListener {
            lifecycleScope.launch {
                val orderId = foodId
                AppStateRepository.update { appState ->
                    val updatedOrders = appState.orders.map { order ->
                        if (order.id == orderId) {
                            order.copy(status = OrderStatus.DELIVERED)
                        } else {
                            order
                        }
                    }
                    appState.copy(orders = updatedOrders.toSet())
                }
            }
        }
    }
}
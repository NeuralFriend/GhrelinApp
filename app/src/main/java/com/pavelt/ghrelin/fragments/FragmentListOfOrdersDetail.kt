package com.pavelt.ghrelin.fragments

import android.os.Bundle
import android.system.Os.bind
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.databinding.FragmentListOfOrdersDetailBinding
import com.pavelt.ghrelin.domain.OrderStatus
import com.pavelt.ghrelin.domain.UserRole
import io.ktor.utils.io.*
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
            val user = AppStateRepository.get().currUser

            if (user!!.role == UserRole.WAITER) {
                btnWaiterAccept.isVisible = true
                btnOrderDoneWaiter.isVisible = true
            } else if (user.role == UserRole.COOK) {
                btnCookAccept.isVisible = true
                btnOrderDoneCook.isVisible = true
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
            Toast.makeText(requireContext(), "Waiter", Toast.LENGTH_SHORT).show()
        }
    }
}

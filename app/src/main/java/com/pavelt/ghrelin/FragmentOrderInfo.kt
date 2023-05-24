package com.pavelt.ghrelin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.domain.Order
import com.pavelt.ghrelin.domain.OrderStatus
import io.ktor.client.utils.EmptyContent.status
import kotlinx.coroutines.launch

class FragmentOrderInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_info, container, false)
        val tvTabNum = view.findViewById<TextView>(R.id.TabNum)
        val tvOrdSt = view.findViewById<TextView>(R.id.OrdStatus)
        val etTableNumb = view.findViewById<EditText>(R.id.etTableNumb)
        val btnRefresh = view.findViewById<Button>(R.id.btnRefresh)
        btnRefresh.isEnabled = false

        etTableNumb.doOnTextChanged { text, start, before, count ->
            btnRefresh.isEnabled = !etTableNumb.text.isNullOrEmpty()
        }

        fun getStatusText(status: OrderStatus): String {
            return when (status) {
                OrderStatus.CREATED -> "Заказ создан"
                OrderStatus.COOKED -> "Заказ приготовлен"
                OrderStatus.COOKING -> "Заказ готовится"
                OrderStatus.DELIVERED -> "Заказ у вас, приятного аппетита!"
                OrderStatus.DELIVERING -> "Заказ скоро будет у вас"
                else ->
                    "Извините, заказа на этот стол не существует"
            }
        }

        btnRefresh.setOnClickListener {
            lifecycleScope.launch {
                val tableNumber = etTableNumb.text.toString().toInt()
                val orders = mutableListOf<Order>()
                val order = findOrderByTableNumber(tableNumber)

                if (order != null) {
                    orders.add(order)
                    val ooo = AppStateRepository.get()
                    val newList = ooo.orders.toList().filter { it.tableNumber == tableNumber }
                        .joinToString("\n") { getStatusText(it.status) }

                    tvTabNum.text = "Номер стола: " + order?.tableNumber.toString()
                    tvOrdSt.text = newList
                } else {
                    Toast.makeText(
                        requireContext(),
                        "За этим столом нет заказов.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        return view
    }

    private suspend fun findOrderByTableNumber(tableNumber: Int): Order? {
        val orders = AppStateRepository.get().orders
        return orders.find { it.tableNumber == tableNumber }
    }
}
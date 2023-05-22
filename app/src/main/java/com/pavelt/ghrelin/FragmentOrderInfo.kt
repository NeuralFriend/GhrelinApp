package com.pavelt.ghrelin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.pavelt.ghrelin.data.AppStateRepository
import com.pavelt.ghrelin.domain.Order
import kotlinx.coroutines.launch

class FragmentOrderInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_info, container, false)
        val tvTabNum = view.findViewById<TextView>(R.id.TabNum)
        val tvOrdSt = view.findViewById<TextView>(R.id.OrdStatus)
        val etTableNumb = view.findViewById<EditText>(R.id.etTableNumb)
        val btnRefresh = view.findViewById<Button>(R.id.btnRefresh)

btnRefresh.setOnClickListener {
    lifecycleScope.launch {
        val tableNumber = etTableNumb.text.toString().toInt()
        val order = findOrderByTableNumber(tableNumber)

        tvOrdSt.text = order?.status.toString()
        tvTabNum.text = order?.tableNumber.toString()
    }
}
        return view
    }
    private suspend fun findOrderByTableNumber(tableNumber: Int): Order? {
        val orders = AppStateRepository.get().orders
        return orders.find { it.tableNumber == tableNumber }
    }
}
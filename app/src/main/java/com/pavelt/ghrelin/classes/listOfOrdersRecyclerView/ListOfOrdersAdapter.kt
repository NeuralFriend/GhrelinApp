package com.pavelt.ghrelin.classes.listOfOrdersRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.classes.menuRecyclerView.OnItemClickListener
import com.pavelt.ghrelin.domain.Order

class ListOfOrdersAdapter(var orders: List<Order>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ListOfOrdersAdapter.OrdersViewHolder>() {

    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countOfElements: TextView = itemView.findViewById(R.id.tvCountOfElements)
        val tableNumber: TextView = itemView.findViewById(R.id.tvTableNumber)
        val orderCost: TextView = itemView.findViewById(R.id.tvCost)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_orders, parent, false)
        return OrdersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.apply {
            countOfElements.text = orders[position].items.count().toString()
            orderCost.text = orders[position].status.toString()
            tableNumber.text = orders[position].tableNumber.toString()

            val id = orders[position].id

            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(position, id)
            }
        }
    }
}
package com.pavelt.ghrelin.classes.basketRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.domain.Food

class BasketRecyclerAdapter(var basket: List<Food>) :
    RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>() {

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val imageView: ImageView = itemView.findViewById(R.id.toolsPhotos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return BasketRecyclerAdapter.BasketViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return basket.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.apply {
            foodName.text = basket[position].name
            foodPrice.text = basket[position].price.toString()

            Glide.with(holder.itemView.context)
                .load(basket[position].imageUrl)
                .into(holder.imageView)

            val id = basket[position].id
        }
    }
}
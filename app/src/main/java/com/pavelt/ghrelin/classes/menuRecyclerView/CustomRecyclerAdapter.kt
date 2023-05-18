package com.pavelt.ghrelin.classes.menuRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.domain.Food

class CustomRecyclerAdapter(var menu: List<Food>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodWeight: TextView = itemView.findViewById(R.id.foodWeight)
        val imageView: ImageView = itemView.findViewById(R.id.toolsPhotos)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = menu.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            foodName.text = menu[position].name
            foodWeight.text = menu[position].weight

            Glide.with(holder.itemView.context)
                .load(menu[position].imageUrl)
                .into(holder.imageView)

            val id = menu[position].id

            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(position, id)
            }
        }
    }
}
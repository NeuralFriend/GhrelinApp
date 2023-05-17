package com.pavelt.ghrelin.classes.menuRecyclerView

import com.pavelt.ghrelin.model.Food

interface FoodRepository {
//    fun getAllData(): List<Food>
    fun getDataById(id: Int): Food?
//    fun addData(data: Food): Boolean
//    fun updateData(data: Food): Boolean
//    fun deleteDataById(id: Int): Boolean
}
package com.pavelt.ghrelin.classes.menuRecyclerView

import com.pavelt.ghrelin.model.Food

class FoodRepositoryImpl : FoodRepository {

    val pizzaUrl = "https://i2.photo.2gis.com/images/branch/0/30258560074074185_a400.jpg"
    val rollUrl =
        "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663721773_50-mykaleidoscope-ru-p-slozhnie-rolli-yeda-pinterest-60.jpg"

    val data = mutableListOf(
        Food(0, "Пепперони", "Пицца", "",1000, pizzaUrl),
        Food(1, "Такуяма", "Роллы", "",1000, rollUrl)
    )

    override fun getDataById(id: Int): Food? {
        return data.find { it.id == id }
    }

//    override fun getAllData(): List<Food> {
//        return data.toList()
//    }
//    override fun addData(data: Food): Boolean {
//        if (this.data.contains(data)) {
//            return false
//        }
//        return this.data.add(data)
//    }
//
//    override fun updateData(data: Food): Boolean {
//        val index = this.data.indexOfFirst { it.id == data.id }
//        if (index == -1) {
//            return false
//        }
//        this.data[index] = data
//        return true
//    }
//
//    override fun deleteDataById(id: Int): Boolean {
//        return this.data.removeIf { it.id == id }
//    }
}
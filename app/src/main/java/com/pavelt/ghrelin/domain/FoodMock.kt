package com.pavelt.ghrelin.domain

object FoodMock {

    val pizzaUrl = "https://i2.photo.2gis.com/images/branch/0/30258560074074185_a400.jpg"
    val rollUrl =
        "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663721773_50-mykaleidoscope-ru-p-slozhnie-rolli-yeda-pinterest-60.jpg"

    val data = mutableListOf(
        Food(0, "Пепперони", "Пицца", "", 1000, pizzaUrl),
        Food(1, "Такуяма", "Роллы", "", 1000, rollUrl)
    )

    fun getFoodById(id: Int): Food? =
        data.find { it.id == id }
}
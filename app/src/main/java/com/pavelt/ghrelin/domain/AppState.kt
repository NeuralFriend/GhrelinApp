package com.pavelt.ghrelin.domain

import kotlinx.serialization.Serializable

@Serializable
data class AppState(
    val currUser: User? = null,
    val orders: Set<Order> = emptySet(),
    val cart: Cart = Cart(),
) {

    fun withNewOrder(order: Order): AppState = copy(
        orders = orders + order,
    )

    fun withClearCart(): AppState = copy(
        cart = Cart(),
    )
}

@Serializable
data class Cart(
    val items: Set<Food> = emptySet(),
) {

    fun withNewItem(food: Food): Cart = copy(
        items = items + food,
    )

    fun hasItem(id: Int): Boolean =
        items.any { it.id == id }
}
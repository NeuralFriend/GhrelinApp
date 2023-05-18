package com.pavelt.ghrelin.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Order(
    val id: String = UUID.randomUUID().toString(),
    val items: Set<Food>,
    val tableNumber: Int,
    val status: OrderStatus = OrderStatus.CREATED,
) {

    init {
        require(items.isNotEmpty())
        require(tableNumber in 1..100)
    }
}

enum class OrderStatus {

    CREATED, COOKING, COOKED, DELIVERING, DELIVERED, PAID, CANCELLED;

    val isVisibleForClient: Boolean
        get() = true

    val isVisibleForCook: Boolean
        get() = this in CREATED..COOKED

    val isVisibleForWaiter: Boolean
        get() = this in COOKED..PAID
}
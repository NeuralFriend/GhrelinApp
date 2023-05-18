package com.pavelt.ghrelin.domain

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: Int,
    val name: String,
    val category: String,
    val weight: String,
    val price: Int,
    val imageUrl: String
)
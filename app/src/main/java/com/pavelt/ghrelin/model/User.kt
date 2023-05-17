package com.pavelt.ghrelin.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String,
    val token: String
)
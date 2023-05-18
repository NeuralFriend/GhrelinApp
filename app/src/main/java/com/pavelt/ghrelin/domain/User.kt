package com.pavelt.ghrelin.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String,
    val role: UserRole,
)

enum class UserRole {
    CLIENT, COOK, WAITER
}
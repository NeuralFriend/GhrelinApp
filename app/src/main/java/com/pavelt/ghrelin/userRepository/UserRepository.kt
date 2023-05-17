package com.pavelt.ghrelin.userRepository

import com.pavelt.ghrelin.model.User

interface UserRepository {
    fun getUserById(id: Int): User?

    fun getUserLogin(login: String): User?

    fun getUserPassword(login: String): User?

    fun findUserBy(login: String, password: String): Int?
}
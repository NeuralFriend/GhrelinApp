package com.pavelt.ghrelin.userRepository

import com.pavelt.ghrelin.model.User
import kotlin.math.log

class UserRepositoryImpl : UserRepository {

    val data = mutableListOf(
        User(1, "Игорь", "Повар", "1", "1", "1"),
        User(2, "Василий", "Официант", "2", "2", "2"),
        User(3, "Мария", "Клиент", "3", "3", "3")
    )


    override fun getUserById(id: Int): User? {
        return data.find { it.id == id }
    }

    override fun getUserLogin(login: String): User? {
        return data.find { it.login == login }
    }

    override fun getUserPassword(password: String): User? {
        return data.find { it.password == password }
    }

    override fun findUserBy(login: String, password: String): Int? {
        val user = data.find { it.login == login && it.password == password }
        return user?.id
    }
}
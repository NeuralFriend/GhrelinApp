package com.pavelt.ghrelin.userRepository

import com.pavelt.ghrelin.domain.User
import com.pavelt.ghrelin.domain.UserRole

class UserRepositoryImpl : UserRepository {

    val data = mutableListOf(
        User(1, "Игорь", "Повар", "1", "1", UserRole.COOK),
        User(2, "Василий", "Официант", "2", "2", UserRole.WAITER),
        User(3, "Мария", "Клиент", "3", "3", UserRole.CLIENT)
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

    override fun findUserBy(login: String, password: String): User? {
        return data.find { it.login == login && it.password == password }
    }
}
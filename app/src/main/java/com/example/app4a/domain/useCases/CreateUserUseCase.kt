package com.example.app4a.domain.useCases

import com.example.app4a.data.repository.UserRepo
import com.example.app4a.domain.entities.User

// Uses Cases -> Domain dans clean architecture
// Un use case est créé par fonctionnalités
// suspend -> Coroutine en asynchrone

class CreateUserUseCase(
    private val userRepo : UserRepo
) {
    suspend fun invoke(user : User) {
        userRepo.createUser(user)
    }
}
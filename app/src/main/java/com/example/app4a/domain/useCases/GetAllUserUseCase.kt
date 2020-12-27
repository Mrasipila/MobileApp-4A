package com.example.app4a.domain.useCases

import com.example.app4a.data.repository.UserRepo
import com.example.app4a.domain.entities.User

class GetAllUserUseCase(
    private val userRepo : UserRepo
    ) {
        suspend fun invoke(user : User): String {
            return userRepo.getAllUser(user)
        }
}
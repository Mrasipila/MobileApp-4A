package com.example.app4a.domain.useCases

import com.example.app4a.data.repository.UserRepo
import com.example.app4a.domain.entities.User

class GetUserUseCase(
    private val userRepo : UserRepo
) {
    suspend fun invoke(email : String, password : String) : User? {
        return userRepo.getUser(email,password)
    }
}
package com.example.app4a.data.repository

import com.example.app4a.data.local.DatabaseDao
import com.example.app4a.data.local.models.toData
import com.example.app4a.data.local.models.toEntity
import com.example.app4a.domain.entities.User

class UserRepo(private val databaseDao: DatabaseDao) {
    suspend fun createUser(user: User){
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String) : User? {
        val userLocal = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }
}
package com.example.app4a.data.repository

import com.example.app4a.data.local.DatabaseDao
import com.example.app4a.data.local.models.toData
import com.example.app4a.data.local.models.toEntity
import com.example.app4a.domain.entities.User

class UserRepo(private val databaseDao: DatabaseDao) {

    suspend fun createUser(user: User) {
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String, password : String) : User? {
        val userLocal = databaseDao.findByName(email,password)
        return userLocal?.toEntity()
    }

    suspend fun getAllUser(user : User): String {
        val builder = StringBuilder()
        val allUser = databaseDao.getAll()
        val itAllUser = allUser.iterator()
        itAllUser.forEach {
            val tmp = it.toEntity()
            builder.append(tmp.email + " | " + tmp.password)
        }
        return builder.toString().trim()
    }

    suspend fun deleteAllUser(){
        databaseDao.nukeTable()
    }
}
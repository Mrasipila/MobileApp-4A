package com.example.app4a.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app4a.data.local.models.UserLocal

// ON LISTE LES ENTITIES EN DESSOUS
@Database(entities = arrayOf(
    UserLocal::class
), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}

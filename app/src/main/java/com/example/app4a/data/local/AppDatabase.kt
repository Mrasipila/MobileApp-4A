package com.example.app4a.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app4a.data.local.models.UserLocal

// ON liste les entity stockées dans la db
@Database(entities = arrayOf(
    UserLocal::class
), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}


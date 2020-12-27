package com.example.app4a.injection

import android.content.Context
import androidx.room.Room
import com.example.app4a.data.local.AppDatabase
import com.example.app4a.data.local.DatabaseDao
import com.example.app4a.data.repository.UserRepo
import com.example.app4a.domain.useCases.CreateUserUseCase
import com.example.app4a.domain.useCases.GetUserUseCase
import com.example.app4a.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val myModule = module {
    factory { MainViewModel(get(), get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single{ UserRepo(get()) }
    single { createDatabase(androidContext()) }
}

fun createDatabase(context: Context) : DatabaseDao {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "db_1"
    ).fallbackToDestructiveMigration()
        .build()
    return appDatabase.databaseDao()
}
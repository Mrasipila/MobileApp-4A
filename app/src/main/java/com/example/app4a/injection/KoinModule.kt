package com.example.app4a.injection

import com.example.app4a.data.repository.UserRepo
import com.example.app4a.domain.useCases.CreateUserUseCase
import com.example.app4a.presentation.main.MainViewModel
import org.koin.dsl.module


val myModule = module {
    factory { MainViewModel(get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
}

val dataModule = module {
    single{ UserRepo() }
}
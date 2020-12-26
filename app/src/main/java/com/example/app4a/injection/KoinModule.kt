package com.example.app4a.injection

import org.koin.dsl.module

// Given some classes
class Controller(val service : BusinessService)
class BusinessService()

// just declare it
val myModule = module {
    single { Controller(get()) }
    single { BusinessService() }
}
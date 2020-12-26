package com.example.app4a.injection

import com.example.app4a.presentation.main.appViewModel
import org.koin.dsl.module


val myModule = module {
    factory { appViewModel() }
}
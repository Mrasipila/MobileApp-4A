package com.example.app4a.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class appApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@appApplication)
            modules(myModule)
        }
    }
}
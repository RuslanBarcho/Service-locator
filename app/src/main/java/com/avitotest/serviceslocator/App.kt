package com.avitotest.serviceslocator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import com.avitotest.serviceslocator.di.modules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}

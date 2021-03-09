package com.softwareuk

import android.app.Application
import com.softwareuk.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(MainModule.create()))
        }
    }
}
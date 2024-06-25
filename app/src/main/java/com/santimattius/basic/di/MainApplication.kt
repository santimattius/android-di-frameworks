package com.santimattius.basic.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                com.santimattius.basic.di.di.DataModule().module,
                com.santimattius.basic.di.di.AppModule().module,
                defaultModule
            )
        }
    }
}
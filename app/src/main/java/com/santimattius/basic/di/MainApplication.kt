package com.santimattius.basic.di

import android.app.Application
import com.santimattius.basic.di.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module
import com.santimattius.basic.di.di.DataModule

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                DataModule().module,
                AppModule().module,
                defaultModule
            )
        }
    }
}
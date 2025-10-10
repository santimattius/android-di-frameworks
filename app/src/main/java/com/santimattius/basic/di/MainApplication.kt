package com.santimattius.basic.di

import android.app.Application
import com.santimattius.basic.di.di.AppModule
import com.santimattius.basic.di.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.dsl.module
import org.koin.ksp.generated.defineComSantimattiusBasicDiDataRepositoriesMovieRepository
import org.koin.ksp.generated.defineComSantimattiusBasicDiMainViewModel
import org.koin.ksp.generated.startKoin

@KoinApplication(
    modules = [AppModule::class, DataModule::class]
)
object MainKoinApplication

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MainKoinApplication.startKoin {
            androidContext(this@MainApplication)
            modules(modules = module {
                defineComSantimattiusBasicDiMainViewModel()
                defineComSantimattiusBasicDiDataRepositoriesMovieRepository()
            })
        }
    }
}
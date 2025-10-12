package com.santimattius.basic.di.di

import android.content.Context
import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.network.RequestInterceptor
import com.santimattius.basic.di.core.network.RetrofitServiceCreator
import com.santimattius.basic.skeleton.BuildConfig
import jakarta.inject.Named
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module
@Configuration
class AppModule {

    @Singleton
    @Named("api_key")
    fun provideApiKey() = BuildConfig.apiKey


    @Singleton
    @Named("base_url")
    @Suppress("FunctionOnlyReturningConstant")
    fun provideBaseUrl() = "https://api.themoviedb.org"

    @Singleton
    fun provideRetrofitCreator(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient
    ): RetrofitServiceCreator {
        return RetrofitServiceCreator(
            baseUrl = baseUrl,
            client = okHttpClient
        )
    }


    @Singleton
    fun provideOkHttpClient(@Named("api_key") apiKey: String) = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor(apiKey = apiKey))
        .build()

    @Singleton
    fun provideAppDataBase(context: Context) = AppDataBase.get(context)
}
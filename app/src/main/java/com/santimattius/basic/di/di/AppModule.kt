package com.santimattius.basic.di.di

import android.content.Context
import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.network.RequestInterceptor
import com.santimattius.basic.di.core.network.RetrofitServiceCreator
import com.santimattius.basic.skeleton.BuildConfig
import okhttp3.OkHttpClient
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Factory
    @Named("api_key")
    fun provideApiKey() = BuildConfig.apiKey


    @Factory
    @Named("base_url")
    @Suppress("FunctionOnlyReturningConstant")
    fun provideBaseUrl() = "https://api.themoviedb.org"

    @Single
    fun provideRetrofitCreator(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient
    ): RetrofitServiceCreator {
        return RetrofitServiceCreator(
            baseUrl = baseUrl,
            client = okHttpClient
        )
    }

    @Single
    fun provideOkHttpClient(@Named("api_key") apiKey: String) = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor(apiKey = apiKey))
        .build()

    @Single
    fun provideAppDataBase(context: Context) = AppDataBase.get(context)
}
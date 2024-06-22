package com.santimattius.basic.di.di

import android.content.Context
import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.network.RequestInterceptor
import com.santimattius.basic.di.core.network.RetrofitServiceCreator
import com.santimattius.basic.skeleton.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("api_key")
    fun provideApiKey() = BuildConfig.apiKey


    @Provides
    @Named("base_url")
    @Suppress("FunctionOnlyReturningConstant")
    fun provideBaseUrl() = "https://api.themoviedb.org"

    @Provides
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

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("api_key") apiKey: String) = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor(apiKey = apiKey))
        .build()

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context) = AppDataBase.get(context)
}
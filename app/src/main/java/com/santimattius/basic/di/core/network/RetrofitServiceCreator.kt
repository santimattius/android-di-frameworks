package com.santimattius.basic.di.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class RetrofitServiceCreator(
    baseUrl: String,
    client: OkHttpClient = OkHttpClient(),
) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S : Any> create(clazz: KClass<S>): S = retrofit.create(clazz.java)
}

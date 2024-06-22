package com.santimattius.basic.di.di

import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.network.RetrofitServiceCreator
import com.santimattius.basic.di.core.network.TheMovieDBService
import com.santimattius.basic.di.data.datasources.MovieLocalDataSource
import com.santimattius.basic.di.data.datasources.MovieRemoteDataSource
import com.santimattius.basic.di.data.datasources.local.RoomMovieDataSource
import com.santimattius.basic.di.data.datasources.remote.RetrofitMovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(retrofitServiceCreator: RetrofitServiceCreator): MovieRemoteDataSource {
        return RetrofitMovieDataSource(retrofitServiceCreator.create(TheMovieDBService::class))
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(appDataBase: AppDataBase): MovieLocalDataSource {
        return RoomMovieDataSource(appDataBase)
    }
}
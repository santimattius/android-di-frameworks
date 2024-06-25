package com.santimattius.basic.di.di

import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.network.RetrofitServiceCreator
import com.santimattius.basic.di.core.network.TheMovieDBService
import com.santimattius.basic.di.data.datasources.MovieLocalDataSource
import com.santimattius.basic.di.data.datasources.MovieRemoteDataSource
import com.santimattius.basic.di.data.datasources.local.RoomMovieDataSource
import com.santimattius.basic.di.data.datasources.remote.RetrofitMovieDataSource
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DataModule {

    @Single
    fun provideMovieRemoteDataSource(retrofitServiceCreator: RetrofitServiceCreator): MovieRemoteDataSource {
        return RetrofitMovieDataSource(retrofitServiceCreator.create(TheMovieDBService::class))
    }

    @Single
    fun provideMovieLocalDataSource(appDataBase: AppDataBase): MovieLocalDataSource {
        return RoomMovieDataSource(appDataBase)
    }
}
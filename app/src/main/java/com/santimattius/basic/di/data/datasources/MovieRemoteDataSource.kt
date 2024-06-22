package com.santimattius.basic.di.data.datasources

import com.santimattius.basic.di.data.datasources.remote.MovieDto

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): Result<List<MovieDto>>
}
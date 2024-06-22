package com.santimattius.basic.di.data.datasources.remote

import com.santimattius.basic.di.core.network.TheMovieDBService
import com.santimattius.basic.di.data.datasources.MovieRemoteDataSource
import com.santimattius.basic.di.data.datasources.remote.MovieDto as TheMovieDbMovie

internal class RetrofitMovieDataSource(
    private val service: TheMovieDBService
) : MovieRemoteDataSource {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getPopularMovies(): Result<List<TheMovieDbMovie>> {
        return try {
            val response = service.getMoviePopular(version = DEFAULT_VERSION, page = SINGLE_PAGE)
            Result.success(response.results)
        } catch (ex: Throwable) {
            Result.failure(ex)
        }
    }

    companion object {
        private const val SINGLE_PAGE = 1
        const val DEFAULT_VERSION = 3
    }
}

package com.santimattius.basic.di.data.repositories

import com.santimattius.basic.di.data.datasources.MovieLocalDataSource
import com.santimattius.basic.di.data.datasources.MovieRemoteDataSource
import com.santimattius.basic.di.data.dtoToEntity
import com.santimattius.basic.di.data.entityToDomain
import com.santimattius.basic.di.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) {

    val all: Flow<List<Movie>> = localDataSource.all.map { it.entityToDomain() }

    suspend fun refresh(): Result<Unit> {
        return remoteDataSource.getPopularMovies()
            .fold(
                onSuccess = { movies ->
                    localDataSource.save(movies.dtoToEntity())
                    Result.success(Unit)
                },
                onFailure = {
                    Result.failure(RefreshMovieFailed())
                }
            )
    }
}

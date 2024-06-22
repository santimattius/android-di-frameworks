package com.santimattius.basic.di.data.datasources.local

import com.santimattius.basic.di.core.database.AppDataBase
import com.santimattius.basic.di.core.database.MovieDao
import com.santimattius.basic.di.data.datasources.MovieLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RoomMovieDataSource(
    appDataBase: AppDataBase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieLocalDataSource {

    private val dao: MovieDao = appDataBase.dao()

    override val all: Flow<List<MovieEntity>>
        get() = dao.getAll()
            .flowOn(dispatcher)

    override suspend fun save(movies: List<MovieEntity>): Result<Boolean> {
        return runSafe {
            deleteAndInsert(*movies.toTypedArray()); true
        }
    }

    private suspend fun <R> runSafe(block: suspend MovieDao.() -> R): Result<R> {
        return withContext(dispatcher) {
            dao.runCatching { block() }
        }
    }
}
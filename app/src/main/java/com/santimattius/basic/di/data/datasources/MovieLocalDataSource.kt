package com.santimattius.basic.di.data.datasources

import com.santimattius.basic.di.data.datasources.local.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    val all: Flow<List<MovieEntity>>

    suspend fun save(movies: List<MovieEntity>): Result<Boolean>

}
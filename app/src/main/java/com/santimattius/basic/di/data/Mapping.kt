package com.santimattius.basic.di.data

import com.santimattius.basic.di.data.datasources.remote.MovieDto
import com.santimattius.basic.di.data.datasources.local.MovieEntity
import com.santimattius.basic.di.domain.Movie

/**
 * map entity to domain model
 *
 * @return movie domain model
 */
internal fun List<MovieEntity>.entityToDomain(): List<Movie> {
    return this.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster = it.poster
        )
    }
}

internal fun List<MovieDto>.dtoToEntity(): List<MovieEntity> {
    return map {
        MovieEntity(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster = it.poster
        )
    }
}
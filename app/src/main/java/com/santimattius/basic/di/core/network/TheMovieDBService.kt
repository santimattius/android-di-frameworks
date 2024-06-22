package com.santimattius.basic.di.core.network

import com.santimattius.basic.di.data.datasources.remote.MovieDto
import com.santimattius.basic.di.data.datasources.remote.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("/{version}/movie/popular")
    suspend fun getMoviePopular(
        @Path("version") version: Int,
        @Query("page") page: Int,
    ): Response<MovieDto>

}

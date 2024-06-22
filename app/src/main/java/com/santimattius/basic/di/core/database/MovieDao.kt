package com.santimattius.basic.di.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.santimattius.basic.di.data.datasources.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieEntity>>

    @Insert
    suspend fun insertAll(vararg movies: MovieEntity)

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(vararg movies: MovieEntity) {
        deleteAll()
        insertAll(*movies)
    }
}
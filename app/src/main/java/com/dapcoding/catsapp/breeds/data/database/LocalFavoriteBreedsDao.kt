package com.dapcoding.catsapp.breeds.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalFavoriteBreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteLocation(breed: BreedEntity)

    @Delete
    suspend fun deleteFavoriteLocation(breed: BreedEntity)

    @Query("SELECT * FROM breedentity")
    fun getFavoriteBreeds(): Flow<List<BreedEntity>>
}
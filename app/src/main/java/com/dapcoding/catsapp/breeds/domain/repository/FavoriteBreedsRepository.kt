package com.dapcoding.catsapp.breeds.domain.repository

import com.dapcoding.catsapp.breeds.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface FavoriteBreedsRepository {
    suspend fun saveFavoriteBreed(breed: Breed)

    suspend fun deleteFavoriteBreed(breed: Breed)

    fun getFavoriteBreeds(): Flow<List<Breed>>
}
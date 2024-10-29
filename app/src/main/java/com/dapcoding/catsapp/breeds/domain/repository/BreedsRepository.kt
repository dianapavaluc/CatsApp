package com.dapcoding.catsapp.breeds.domain.repository

import com.dapcoding.catsapp.breeds.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {
    suspend fun getBreeds(): Flow<List<Breed>>
}
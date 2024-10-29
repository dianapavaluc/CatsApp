package com.dapcoding.catsapp.breeds.data.repository

import com.dapcoding.catsapp.breeds.data.database.LocalFavoriteBreedsDao
import com.dapcoding.catsapp.breeds.data.mappers.toBreed
import com.dapcoding.catsapp.breeds.data.mappers.toBreedEntity
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.repository.FavoriteBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteBreedsRepositoryImpl(
    private val localFavoriteBreedsDao: LocalFavoriteBreedsDao
) : FavoriteBreedsRepository {
    override suspend fun saveFavoriteBreed(breed: Breed) {
        localFavoriteBreedsDao.insertFavoriteLocation(breed.toBreedEntity())
    }

    override suspend fun deleteFavoriteBreed(breed: Breed) {
        localFavoriteBreedsDao.deleteFavoriteLocation(breed.toBreedEntity())
    }

    override fun getFavoriteBreeds(): Flow<List<Breed>> {
        return localFavoriteBreedsDao.getFavoriteBreeds().map { breeds ->
            breeds.map { it.toBreed() }
        }
    }
}
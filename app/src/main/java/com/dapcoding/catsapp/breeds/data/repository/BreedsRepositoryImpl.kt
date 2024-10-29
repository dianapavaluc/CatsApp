package com.dapcoding.catsapp.breeds.data.repository

import com.dapcoding.catsapp.breeds.data.networking.RemoteBreedDataSource
import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.repository.BreedsRepository
import com.dapcoding.catsapp.breeds.domain.repository.FavoriteBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class BreedsRepositoryImpl(
    private val remote: RemoteBreedDataSource,
    private val local: FavoriteBreedsRepository,
) : BreedsRepository {
    override suspend fun getBreeds(): Flow<List<Breed>> = flow {
        val localFavoriteBreedsMap = local.getFavoriteBreeds().firstOrNull().orEmpty().map {
            it.id
        }.toSet()
        val remoteBreeds: List<Breed> = remote.getBreeds()?.map {
            it.copy(
                isFavorite = localFavoriteBreedsMap.contains(it.id)
            )
        } ?: emptyList()

        emit(remoteBreeds)
    }
}
package com.dapcoding.catsapp.breeds.data.networking

import com.dapcoding.catsapp.breeds.data.mappers.toBreed
import com.dapcoding.catsapp.breeds.domain.model.Breed
import javax.inject.Inject

class RemoteBreedDataSource @Inject constructor(
    private val api: BreedsApi
) : BreedDataSource {
    override suspend fun getBreeds(): List<Breed>? {
        return api.getBreeds()?.map {
            it.toBreed()
        }
    }
}

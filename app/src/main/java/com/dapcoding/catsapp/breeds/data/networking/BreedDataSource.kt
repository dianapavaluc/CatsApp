package com.dapcoding.catsapp.breeds.data.networking

import com.dapcoding.catsapp.breeds.domain.model.Breed

interface BreedDataSource {
    suspend fun getBreeds(): List<Breed>?
}
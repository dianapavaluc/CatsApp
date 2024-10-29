package com.dapcoding.catsapp.breeds.domain.usecases

import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository
) {
    suspend operator fun invoke(): Flow<List<Breed>> = breedsRepository.getBreeds()
}
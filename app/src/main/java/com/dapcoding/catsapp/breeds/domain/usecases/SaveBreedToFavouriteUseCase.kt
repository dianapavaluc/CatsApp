package com.dapcoding.catsapp.breeds.domain.usecases

import com.dapcoding.catsapp.breeds.domain.model.Breed
import com.dapcoding.catsapp.breeds.domain.repository.FavoriteBreedsRepository
import javax.inject.Inject

class SaveBreedToFavouriteUseCase @Inject constructor(
    private val favoriteBreedsRepository: FavoriteBreedsRepository
) {
    suspend operator fun invoke(breed: Breed) {
        favoriteBreedsRepository.saveFavoriteBreed(breed)
    }
}
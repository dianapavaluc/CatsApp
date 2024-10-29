package com.dapcoding.catsapp.breeds.presentation.breeddetails

import com.dapcoding.catsapp.breeds.domain.model.Breed

sealed interface BreedDetailsAction {
    data class OnFavoriteClick(val breed: Breed) : BreedDetailsAction
    data object OnDetailsBack : BreedDetailsAction
    data class OnWikipediaLink(val url: String) : BreedDetailsAction
}
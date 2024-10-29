package com.dapcoding.catsapp.breeds.presentation.breedslist

import com.dapcoding.catsapp.breeds.domain.model.Breed

sealed interface BreedListAction {
    data class OnBreedClick(val breed: Breed) : BreedListAction
    data class OnFavoriteClick(val breed: Breed) : BreedListAction
}
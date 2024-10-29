package com.dapcoding.catsapp.breeds.presentation.breedslist


sealed interface BreedListEvent {
    data class Error(val error: String): BreedListEvent
}